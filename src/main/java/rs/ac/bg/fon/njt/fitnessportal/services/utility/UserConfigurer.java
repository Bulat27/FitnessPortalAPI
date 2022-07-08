package rs.ac.bg.fon.njt.fitnessportal.services.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import rs.ac.bg.fon.njt.fitnessportal.entities.Role;
import rs.ac.bg.fon.njt.fitnessportal.entities.User;
import rs.ac.bg.fon.njt.fitnessportal.repositories.RoleRepository;
import rs.ac.bg.fon.njt.fitnessportal.security.authorization.ApplicationUserRole;

import java.util.List;

@Component
public class UserConfigurer {

    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public void encodePassword(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }

    public void addRoles(User user, List<ApplicationUserRole> roleTypes){
        if(roleTypes == null || roleTypes.isEmpty()) return;

        user.getRoles().clear();
        for (ApplicationUserRole roleType : roleTypes) {
            Role role = roleRepository.findByName(roleType)
                    .orElseThrow(() -> new IllegalStateException(String.format("The role %s doesn't exist in the database", roleType.name())));
            user.addRole(role);
        }
    }

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
}
