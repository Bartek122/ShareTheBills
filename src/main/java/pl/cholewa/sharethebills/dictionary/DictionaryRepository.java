package pl.cholewa.sharethebills.dictionary;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DictionaryRepository extends JpaRepository<Dictionary,Long> {
    Optional<Dictionary> findByName(String Name);
}
