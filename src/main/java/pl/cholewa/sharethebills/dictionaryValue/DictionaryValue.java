package pl.cholewa.sharethebills.dictionaryValue;

import lombok.*;
import pl.cholewa.sharethebills.dictionary.Dictionary;

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
