package pl.cholewa.sharethebills.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAttributeRepository extends JpaRepository<UserAttribute,Long> {
}
