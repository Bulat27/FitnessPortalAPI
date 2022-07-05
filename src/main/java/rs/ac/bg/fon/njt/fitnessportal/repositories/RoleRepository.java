package rs.ac.bg.fon.njt.fitnessportal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.bg.fon.njt.fitnessportal.entities.Role;
import rs.ac.bg.fon.njt.fitnessportal.security.authorization.ApplicationUserRole;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(ApplicationUserRole name);
}
