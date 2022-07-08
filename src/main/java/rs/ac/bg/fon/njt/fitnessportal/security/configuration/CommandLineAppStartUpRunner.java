package rs.ac.bg.fon.njt.fitnessportal.security.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import rs.ac.bg.fon.njt.fitnessportal.dtos.user.UserPostDto;
import rs.ac.bg.fon.njt.fitnessportal.entities.Role;
import rs.ac.bg.fon.njt.fitnessportal.repositories.RoleRepository;
import rs.ac.bg.fon.njt.fitnessportal.repositories.UserRepository;
import rs.ac.bg.fon.njt.fitnessportal.services.UserService;

import java.util.Arrays;

import static rs.ac.bg.fon.njt.fitnessportal.security.authorization.ApplicationUserRole.*;

@Component
public class CommandLineAppStartUpRunner implements CommandLineRunner {

    private UserService userService;
    private RoleRepository roleRepository;
    private UserRepository userRepository;
    private InitialAdminConfiguration initialAdminConfig;

    @Override
    public void run(String... args) throws Exception {
        if(userRepository.existsByEmail(initialAdminConfig.getEmail())) return;

        roleRepository.save(new Role(ADMIN));
        roleRepository.save(new Role(USER));
        roleRepository.save(new Role(COACH));

        UserPostDto adminUser = new UserPostDto(initialAdminConfig.getName(), initialAdminConfig.getLastname(), initialAdminConfig.getEmail(), initialAdminConfig.getPassword());
        userService.create(adminUser, Arrays.asList(ADMIN));
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setInitialAdminConfig(InitialAdminConfiguration initialAdminConfig) {
        this.initialAdminConfig = initialAdminConfig;
    }
}
