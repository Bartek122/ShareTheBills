package pl.cholewa.sharethebills.billDetail;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/billDetail")
public class BillDetailController {

    private final BillDetailService billDetailService;

    @GetMapping
    public List<BillDetailResponse> getDetail(@RequestBody @Valid BillDetailRequest billDetailRequest){
        List<BillDetailResponse> billDetailResponses = billDetailService.getSumByBorrower(billDetailRequest);
        return billDetailResponses;
    }
}
