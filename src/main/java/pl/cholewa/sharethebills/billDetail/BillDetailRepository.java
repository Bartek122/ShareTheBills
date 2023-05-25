package pl.cholewa.sharethebills.billDetail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.cholewa.sharethebills.user.User;

import java.util.List;

public interface BillDetailRepository extends JpaRepository<BillDetail,Long> {
    List<BillDetail> findAllByBorrower(User borrower);

    @Query(name="sum_price_for_user", nativeQuery = true)
    List<BillDetailSumResponse> getSumPriceForUser(
            @Param("loginBorrower") String loginBorrower,
            @Param("groupName") String groupName
    );

}
