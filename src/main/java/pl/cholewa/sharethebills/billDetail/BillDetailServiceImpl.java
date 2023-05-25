package pl.cholewa.sharethebills.billDetail;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.cholewa.sharethebills.user.User;
import pl.cholewa.sharethebills.user.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BillDetailServiceImpl implements BillDetailService{
    private final BillDetailRepository billDetailRepository;
    private final UserRepository userRepository;

    @Override
    public List<BillDetailResponse> getSumByBorrower(BillDetailRequest billDetailRequest) {

        User borrower = userRepository.findByLogin(billDetailRequest.loginBorrower());
        //List<Object[]> objList = billDetailRepository.getSum(borrower);
        List<BillDetailSumResponse> billDetailSumResponses = billDetailRepository.getSumPriceForUser(billDetailRequest.loginBorrower(),billDetailRequest.groupName());
        return billDetailSumResponses.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());

    }



    private BillDetailResponse toResponse(BillDetailSumResponse billDetailSumResponse){
        return new BillDetailResponse(
                billDetailSumResponse.price(),
                billDetailSumResponse.loginPayer()
        );
    }
}
