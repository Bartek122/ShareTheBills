package pl.cholewa.sharethebills.bill;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

public class BillRepositoryTest {
    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    BillRepository billRepository;

    @Test
    public void whenSearchBillById_thenFindOnlyOne(){
        //BillRepository billRepository = testEntityManager.find(Bill.class,1L);
    }
}
