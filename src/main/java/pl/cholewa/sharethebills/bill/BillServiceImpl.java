package pl.cholewa.sharethebills.bill;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.cholewa.sharethebills.billDetail.BillDetail;
import pl.cholewa.sharethebills.billDetail.BillDetailRepository;
import pl.cholewa.sharethebills.group.Group;
import pl.cholewa.sharethebills.group.GroupRepository;
import pl.cholewa.sharethebills.user.User;
import pl.cholewa.sharethebills.user.UserRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BillServiceImpl implements BillService{
    private final BillRepository billRepository;
    private final BillDetailRepository billDetailRepository;
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;


    @Override
    public BillResponse insert(CreateBillRequest billRequest) {
        User payer = userRepository.findByLogin(billRequest.loginPayer());
        Group group = groupRepository.findByName(billRequest.groupName());
        List<User> userList = group.getUsers();
        int userCount = userList.size();
        BigDecimal priceByUser = billRequest.price().divide(new BigDecimal(userCount),10 ,RoundingMode.HALF_UP);

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
    public List<BillResponse> getAllbyPayer(String login) {
        User payer = userRepository.findByLogin(login);
        List<Bill> bills = billRepository.findAllByPayer(payer);
        return bills.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public BillResponse update(UpdateBillRequest billRequest) {
        return null;
    }


    private BillResponse toResponse(Bill bill){
        return new BillResponse(
                bill.getDescription(),
                bill.getSumPrice()
        );
    }

}
