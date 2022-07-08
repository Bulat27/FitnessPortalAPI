package rs.ac.bg.fon.njt.fitnessportal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.bg.fon.njt.fitnessportal.entities.User;

import java.util.Optional;

@Repository
public interface UserBaseRepository<T extends User> extends JpaRepository<T, Integer> {

    Optional<T> findByEmail(String email);
    boolean existsByEmail(String email);
    void deleteByEmail(String email);
}
