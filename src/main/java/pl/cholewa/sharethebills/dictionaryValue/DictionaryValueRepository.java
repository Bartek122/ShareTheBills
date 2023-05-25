package pl.cholewa.sharethebills.dictionaryValue;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DictionaryValueRepository extends JpaRepository<DictionaryValue, Long> {
    Optional<DictionaryValue> findByContent(String content);
}
