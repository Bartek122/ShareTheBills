package pl.cholewa.sharethebills.bill;

import lombok.*;
import pl.cholewa.sharethebills.billDetail.BillDetail;
import pl.cholewa.sharethebills.group.Group;
import pl.cholewa.sharethebills.user.User;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "bills")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private BigDecimal sumPrice;
    @ManyToOne
    private User payer;
    @ManyToOne
    private Group group;
    @OneToMany(mappedBy = "masterBill")
    private List<BillDetail> billDetails;
}
