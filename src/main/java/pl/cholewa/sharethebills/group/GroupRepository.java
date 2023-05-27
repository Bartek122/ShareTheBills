package pl.cholewa.sharethebills.group;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.cholewa.sharethebills.user.User;

import java.util.List;
import java.util.Optional;


public interface GroupRepository extends JpaRepository<Group,Long> {
    Optional<Group> findByNameAndUsers(String name,User user);
    List<Group> findAllBy();
    Optional<Group> findById(Long id);


}
