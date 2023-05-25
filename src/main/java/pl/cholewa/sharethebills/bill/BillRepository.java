package pl.cholewa.sharethebills.bill;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.cholewa.sharethebills.user.User;

import java.util.List;

public interface BillRepository extends JpaRepository<Bill, Long> {

    List<Bill> findAllByPayer(User payer);

}
