package pl.cholewa.sharethebills.bill;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.cholewa.sharethebills.user.User;

import java.util.List;
import java.util.Optional;

public interface BillRepository extends JpaRepository<Bill, Long> {

    List<Bill> findAllByPayer(User payer);
    Optional<Bill> findById(Long id);

    List<Bill> getAllBy();


}
