package pl.cholewa.sharethebills.dictionary;

import lombok.*;
import pl.cholewa.sharethebills.dictionaryValue.DictionaryValue;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="dictionaries")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Dictionary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "dictionary")
    private List<DictionaryValue> dictionaryValueList = new ArrayList<>();
}
