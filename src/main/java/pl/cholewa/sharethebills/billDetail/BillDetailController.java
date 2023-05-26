package pl.cholewa.sharethebills.billDetail;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/{id}")
    public ResponseEntity updateBillDetail(@PathVariable Long id, @RequestBody @Valid UpdateBillDetailRequest request){
        billDetailService.updateBillDetails(id,request);
        return ResponseEntity.ok().build();
    }
}
