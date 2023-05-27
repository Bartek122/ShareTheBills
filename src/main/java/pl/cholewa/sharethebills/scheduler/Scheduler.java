package pl.cholewa.sharethebills.scheduler;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.cholewa.sharethebills.bill.BillResponse;
import pl.cholewa.sharethebills.bill.BillService;

import java.util.List;

@Component
@Slf4j
@AllArgsConstructor
public class Scheduler {
    private final BillService billService;
    @Scheduled(cron = "0 */2 * * * ?")
    public void getAllBill() throws InterruptedException{
        List<BillResponse> billResponses = billService.getAll();
        log.info("Scheduler - data from bill: " + billResponses.toString());
    }
}
