package pl.cholewa.sharethebills.user;

import lombok.*;
import pl.cholewa.sharethebills.group.Group;
import pl.cholewa.sharethebills.userAttribute.UserAttribute;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
@Getter @Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

    @OneToMany(mappedBy = "user")
    private List<UserAttribute> userAttributesList = new ArrayList<>();
    @OneToMany(mappedBy = "owner")
    private List<Group> groupList = new ArrayList<>();
}
