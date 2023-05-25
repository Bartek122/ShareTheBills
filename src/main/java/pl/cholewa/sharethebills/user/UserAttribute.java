package pl.cholewa.sharethebills.user;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "user_attributes")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAttribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User user;
    @Column(nullable = false)
    private String type;
    @Column(nullable = false)
    private String content;



}
