package pl.cholewa.sharethebills.user;

import com.sun.xml.bind.v2.schemagen.xmlschema.AttributeType;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "user_attributes")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserAttribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User userId;
    @Column(nullable = false)
    private String type;
    @Column(nullable = false)
    private String value;

}
