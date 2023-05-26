package pl.cholewa.sharethebills.billDetail;

import lombok.*;
import pl.cholewa.sharethebills.bill.Bill;
import pl.cholewa.sharethebills.user.User;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@NamedNativeQuery(
        name = "sum_price_for_user",
        query=
                "SELECT SUM(bd.price) AS sumPrice, u.login AS borrower, p.login AS payer, g.name AS group_name  FROM bill_details bd JOIN users u ON bd.borrower_id=u.id " +
                        "JOIN bills b ON bd.master_bill_id=b.id " +
                        "JOIN users p ON b.payer_id = p.id " +
                        "JOIN groups g ON b.group_id=g.id " +
                        "WHERE u.login=:loginBorrower " +
                        "AND g.name=:groupName " +
                        "AND u.login<>p.login " +
                        "GROUP BY u.login, p.login,g.name ",
        resultSetMapping = "mapping_sum_price_for_user"
)
@SqlResultSetMapping(
        name = "mapping_sum_price_for_user",
        classes = @ConstructorResult(
                targetClass = BillDetailSumResponse.class,
                columns = {
                        @ColumnResult(name="sumPrice", type = BigDecimal.class),
                        @ColumnResult(name="borrower", type = String.class),
                        @ColumnResult(name="payer", type= String.class),
                        @ColumnResult(name="group_name", type= String.class)
                }
        )
)
@Table(name = "bill_details")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class BillDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal price;
    private boolean isChange;
    @ManyToOne
    private User borrower;
    @ManyToOne
    private Bill masterBill;




}
