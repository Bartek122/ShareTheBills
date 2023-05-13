package pl.cholewa.sharethebills.user;

import lombok.*;
import pl.cholewa.sharethebills.group.Group;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
@Getter @Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String login;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;

    @OneToMany(mappedBy = "userId")
    private List<UserAttribute> userAttributesList = new ArrayList<>();


}
