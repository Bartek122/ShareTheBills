package pl.cholewa.sharethebills.group;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface GroupRepository extends JpaRepository<Group,Long> {
    Group findByName(String name);
    List<Group> findAllBy();
    Optional<Group> findById(Long id);

}
