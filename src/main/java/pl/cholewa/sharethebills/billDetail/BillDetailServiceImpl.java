package pl.cholewa.sharethebills.billDetail;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.cholewa.sharethebills.bill.Bill;
import pl.cholewa.sharethebills.bill.BillRepository;
import pl.cholewa.sharethebills.user.UserRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BillDetailServiceImpl implements BillDetailService{
    private final BillDetailRepository billDetailRepository;
    private final UserRepository userRepository;
    private final BillRepository billRepository;

    @Override
    public List<BillDetailResponse> getSumByBorrower(BillDetailRequest billDetailRequest) {

        List<BillDetailSumResponse> billDetailSumResponses = billDetailRepository.getSumPriceForUser(billDetailRequest.loginBorrower(),billDetailRequest.groupName());
        return billDetailSumResponses.stream()
                .map(this::toSumResponse)
                .collect(Collectors.toList());

    }

    @Override
    @Transactional
    public void updateBillDetails(Long id, UpdateBillDetailRequest request) {
        Optional<BillDetail> oldBillDetail = billDetailRepository.findById(id);
        Bill masterBill = oldBillDetail.get().getMasterBill();
        List<BillDetail> billDetails = billDetailRepository.findAllByMasterBill(masterBill);

        oldBillDetail.map(billDetail -> {
            billDetail.setPrice(request.amount());
            billDetail.setChange(true);
            return billDetail;
        })      .map(billDetailRepository::save)
                .orElseThrow(() -> new IllegalArgumentException("No bill details with id " + id));
        BigDecimal oldSumAmount = masterBill.getSumPrice();
        List<BillDetail> billDetailListWithFalse= billDetails.stream()
                .filter(billDetail -> billDetail.isChange()==false)
                .collect(Collectors.toList());
        List<BillDetail> billDetailListWithTrue=billDetails.stream()
                .filter(billDetail -> billDetail.isChange()==true)
                .collect(Collectors.toList());
        int BillDetailToChange = billDetailListWithFalse.size();
        BigDecimal amountForOtherBorrower = oldSumAmount.subtract(request.amount());

        if(billDetailListWithTrue.size()>0){
            for (BillDetail billDetail:billDetailListWithTrue) {
                if(oldBillDetail.get() != billDetail) {
                    amountForOtherBorrower = amountForOtherBorrower.subtract(billDetail.getPrice());
                }
            }

        }

        if(BillDetailToChange==0){
            BigDecimal sumAmount = (BigDecimal) billDetailListWithTrue.stream()
                    .map(s->s.getPrice())
                    .reduce(BigDecimal.ZERO,BigDecimal::add);
            if(sumAmount.equals(masterBill.getSumPrice())){

            }
            else{
                masterBill.setSumPrice(sumAmount);
                billRepository.save(masterBill);
            }
        } else {
            amountForOtherBorrower=amountForOtherBorrower.divide(new BigDecimal(BillDetailToChange));
            for (BillDetail billDetailWithFalse:billDetailListWithFalse) {
                billDetailWithFalse.setPrice(amountForOtherBorrower);
                billDetailRepository.save(billDetailWithFalse);
            }
        }




    }


    private BillDetailResponse toSumResponse(BillDetailSumResponse billDetailSumResponse){
        return new BillDetailResponse(
                billDetailSumResponse.price(),
                billDetailSumResponse.loginPayer()
        );
    }

}
