package pl.cholewa.sharethebills.bill;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/bill")
public class BillController {
    private final BillService billService;


    public List<BillResponse> getBill(@PathVariable String login){
        List<BillResponse> bills = billService.getAllByPayer(login);
        log.debug("Collected {} bills",bills.size());
        return bills;
    }
    @GetMapping
    public List<BillResponse> getAllBill(){
        List<BillResponse> billResponses = billService.getAll();
        log.debug("Collected {} bills",billResponses.size());
        return billResponses;
    }

    @PutMapping("/{id}")
    public ResponseEntity<BillResponse> updateBill(@PathVariable Long id,@RequestBody @Valid UpdateBillRequest request){
        BillResponse response = billService.update(id, request);
        log.debug("Bill updated {}",response);
        return ResponseEntity.ok(response);
    }
    @PostMapping
    public ResponseEntity<?> insertBill(@RequestBody @Valid CreateBillRequest request){
        BillResponse response = billService.insert(request);
        log.debug("Bill inserted {}",response);
        return ResponseEntity.ok()
                .body(response);
    }
    @DeleteMapping("/{id}")
    public void deleteBill(@PathVariable Long id){
        billService.billDelete(id);
        log.debug("Bill id: {} deleted",id);
    }

}
