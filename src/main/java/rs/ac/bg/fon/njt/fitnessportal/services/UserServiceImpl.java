package rs.ac.bg.fon.njt.fitnessportal.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.ac.bg.fon.njt.fitnessportal.dtos.UserGetDto;
import rs.ac.bg.fon.njt.fitnessportal.dtos.UserPostDto;
import rs.ac.bg.fon.njt.fitnessportal.dtos.UserPutDto;
import rs.ac.bg.fon.njt.fitnessportal.entities.Role;
import rs.ac.bg.fon.njt.fitnessportal.entities.User;
import rs.ac.bg.fon.njt.fitnessportal.exception_handling.AdminCannotBeModifiedException;
import rs.ac.bg.fon.njt.fitnessportal.exception_handling.EmailExistsException;
import rs.ac.bg.fon.njt.fitnessportal.exception_handling.UserNotFoundException;
import rs.ac.bg.fon.njt.fitnessportal.mapstruct.mappers.UserMapper;
import rs.ac.bg.fon.njt.fitnessportal.repositories.RoleRepository;
import rs.ac.bg.fon.njt.fitnessportal.repositories.UserRepository;
import rs.ac.bg.fon.njt.fitnessportal.security.authorization.ApplicationUserRole;
import rs.ac.bg.fon.njt.fitnessportal.security.configuration.InitialAdminConfiguration;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;
    private UserMapper userMapper;
    private PasswordEncoder passwordEncoder;
    private RoleRepository roleRepository;
    private InitialAdminConfiguration initialAdminConfig;

    @Override
    public List<UserGetDto> get() {
        return userMapper.usersToUserGetDtos(userRepository.findAll());
    }

    @Override
    public UserGetDto get(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(email));
        return userMapper.userToUserGetDto(user);
    }

    @Override
    public UserGetDto create(UserPostDto userPostDto, List<ApplicationUserRole> roleTypes) {
        if(userRepository.existsByEmail(userPostDto.getEmail())) throw new EmailExistsException(userPostDto.getEmail());

        User user = userMapper.userPostDtoToUser(userPostDto);
        addRoles(user, roleTypes);
        encodePassword(user);
        userRepository.save(user);
        return userMapper.userToUserGetDto(user);
    }

    @Override
    public UserGetDto update(UserPutDto userPutDto){
        if(userPutDto.getEmail().equals(initialAdminConfig.getEmail())) throw new AdminCannotBeModifiedException();

        User user = userRepository.findByEmail(userPutDto.getEmail()).orElseThrow(() -> new UserNotFoundException(userPutDto.getEmail()));

        userMapper.update(userPutDto, user);
        if(userPutDto.getPassword() != null) encodePassword(user);
        addRoles(user, userPutDto.getRoles());
        return userMapper.userToUserGetDto(userRepository.save(user));
    }

    @Override
    @Transactional
    public void delete(String email) {
        if(email.equals(initialAdminConfig.getEmail())) throw new AdminCannotBeModifiedException();

        if(!userRepository.existsByEmail(email)) throw new UserNotFoundException(email);
        userRepository.deleteByEmail(email);
    }

    private void encodePassword(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }

    private void addRoles(User user, List<ApplicationUserRole> roleTypes){
        if(roleTypes == null || roleTypes.isEmpty()) return;

        user.getRoles().clear();
        for (ApplicationUserRole roleType : roleTypes) {
            Role role = roleRepository.findByName(roleType)
                    .orElseThrow(() -> new IllegalStateException(String.format("The role %s doesn't exist in the database", roleType.name())));
            user.addRole(role);
        }
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Autowired
    public void setInitialAdminConfig(InitialAdminConfiguration initialAdminConfig) {
        this.initialAdminConfig = initialAdminConfig;
    }
}