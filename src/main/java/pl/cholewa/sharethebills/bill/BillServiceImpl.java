package pl.cholewa.sharethebills.bill;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.cholewa.sharethebills.billDetail.BillDetail;
import pl.cholewa.sharethebills.billDetail.BillDetailRepository;
import pl.cholewa.sharethebills.group.Group;
import pl.cholewa.sharethebills.group.GroupRepository;
import pl.cholewa.sharethebills.user.User;
import pl.cholewa.sharethebills.user.UserRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BillServiceImpl implements BillService{
    private final BillRepository billRepository;
    private final BillDetailRepository billDetailRepository;
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;


    @Override
    @Transactional
    public BillResponse insert(CreateBillRequest billRequest) {
        User payer = userRepository.findByLogin(billRequest.loginPayer());
        Group group = groupRepository.findByName(billRequest.groupName());
        List<User> userList = group.getUsers();
        int userCount = userList.size();
        BigDecimal priceByUser = amountPerUser(billRequest.price(), userCount);

        Bill bill = Bill.builder()
                .description(billRequest.description())
                .sumPrice(billRequest.price())
                .payer(payer)
                .group(group)

        .build();
        billRepository.save(bill);

        for (User user: userList) {
            if(user!=payer) {
                BillDetail billDetail = BillDetail.builder()
                        .masterBill(bill)
                        .borrower(user)
                        .price(priceByUser)
                        .build();
                billDetailRepository.save(billDetail);
            }
        }
        return toResponse(bill);
    }


    @Override
    public List<BillResponse> getAllByPayer(String login) {
        User payer = userRepository.findByLogin(login);
        List<Bill> bills = billRepository.findAllByPayer(payer);
        return bills.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public BillResponse update(Long id,UpdateBillRequest billRequest) {
        Optional<Bill> oldBill = billRepository.findById(id);
       return billRepository.findById(id)
                .map(bill -> {
                    if (billRequest.price() !=null){
                        bill.setSumPrice(billRequest.price());
                        User payer = bill.getPayer();
                        List<User> userList = bill.getGroup().getUsers();
                        int userCount = userList.size();
                        BigDecimal priceByUser= amountPerUser(billRequest.price(), userCount);
                        for (User user : userList) {
                            if (user != payer) {
                              BillDetail billDetail =  billDetailRepository.findByBorrowerAndMasterBill(user,billRepository
                                        .findById(id)
                                        .orElseThrow(() -> new IllegalArgumentException("No bill with id" + id)));
                              billDetail.setPrice(priceByUser);
                              billDetailRepository.save(billDetail);
                            }
                        }

                    } else {
                        bill.setSumPrice(oldBill.get().getSumPrice());
                    }
                    if (billRequest.description() !=null){
                        bill.setDescription(billRequest.description());
                    } else {
                        bill.setDescription(oldBill.get().getDescription());
                    }

                    return bill;
                        }

                )
               .map(billRepository::save)
               .map(this::toResponse)
               .orElseThrow(() -> new IllegalArgumentException("No bill with id" + id));


    }

    @Override
    public void billDelete(Long id) {
        billRepository.deleteById(id);
    }


    private BillResponse toResponse(Bill bill){
        return new BillResponse(
                bill.getDescription(),
                bill.getSumPrice()
        );
    }
    private BigDecimal amountPerUser(BigDecimal sumAmount, int userCount){
        return sumAmount.divide(new BigDecimal(userCount),10 ,RoundingMode.HALF_UP);
    }

}
