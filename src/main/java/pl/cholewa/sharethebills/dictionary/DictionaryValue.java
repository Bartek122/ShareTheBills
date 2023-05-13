package pl.cholewa.sharethebills.dictionary;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "dictionary_values")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class DictionaryValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Dictionary dictionary;
    @Column(nullable = false)
    private String content;

}
