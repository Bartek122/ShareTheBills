package pl.cholewa.sharethebills.bill;

import lombok.*;
import pl.cholewa.sharethebills.group.Group;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "bills")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal price;
    private String description;
    @ManyToOne
    private Group group;

}
