package rs.ac.bg.fon.njt.fitnessportal.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.ac.bg.fon.njt.fitnessportal.dtos.user.UserGetDto;
import rs.ac.bg.fon.njt.fitnessportal.dtos.user.UserPostDto;
import rs.ac.bg.fon.njt.fitnessportal.dtos.user.UserProfileGetDto;
import rs.ac.bg.fon.njt.fitnessportal.dtos.user.UserPutDto;
import rs.ac.bg.fon.njt.fitnessportal.entities.User;
import rs.ac.bg.fon.njt.fitnessportal.entities.UserProfileInformation;
import rs.ac.bg.fon.njt.fitnessportal.exception_handling.AdminCannotBeModifiedException;
import rs.ac.bg.fon.njt.fitnessportal.exception_handling.EmailExistsException;
import rs.ac.bg.fon.njt.fitnessportal.exception_handling.UserNotFoundException;
import rs.ac.bg.fon.njt.fitnessportal.mapstruct.mappers.UserMapper;
import rs.ac.bg.fon.njt.fitnessportal.repositories.UserRepository;
import rs.ac.bg.fon.njt.fitnessportal.security.authorization.ApplicationUserRole;
import rs.ac.bg.fon.njt.fitnessportal.security.configuration.InitialAdminConfiguration;
import rs.ac.bg.fon.njt.fitnessportal.services.utility.UserConfigurer;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;
    private UserMapper userMapper;
    private InitialAdminConfiguration initialAdminConfig;
    private UserConfigurer userConfigurer;

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
    public UserProfileGetDto getWithProfile(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(email));

        UserProfileGetDto userProfileGetDto = userMapper.userToUserProfileGetDto(user);

        if(user.getUserProfileInformation() != null) fillProfileInfo(userProfileGetDto, user.getUserProfileInformation());

        return userProfileGetDto;
    }

    @Override
    @Transactional
    public UserGetDto create(UserPostDto userPostDto, List<ApplicationUserRole> roleTypes) {
        if(userRepository.existsByEmail(userPostDto.getEmail())) throw new EmailExistsException(userPostDto.getEmail());

        User user = userMapper.userPostDtoToUser(userPostDto);
        userConfigurer.addRoles(user, roleTypes);
        userConfigurer.encodePassword(user);

        userRepository.save(user);
        return userMapper.userToUserGetDto(user);
    }

    @Override
    @Transactional
    public UserGetDto update(UserPutDto userPutDto){
        if(userPutDto.getEmail().equals(initialAdminConfig.getEmail())) throw new AdminCannotBeModifiedException();

        User user = userRepository.findByEmail(userPutDto.getEmail()).orElseThrow(() -> new UserNotFoundException(userPutDto.getEmail()));

        userMapper.update(userPutDto, user);
        if(userPutDto.getPassword() != null) userConfigurer.encodePassword(user);
        userConfigurer.addRoles(user, userPutDto.getRoles());
        return userMapper.userToUserGetDto(userRepository.save(user));
    }

    @Override
    @Transactional
    public void delete(String email) {
        if(email.equals(initialAdminConfig.getEmail())) throw new AdminCannotBeModifiedException();

        if(!userRepository.existsByEmail(email)) throw new UserNotFoundException(email);
        userRepository.deleteByEmail(email);
    }

    private void fillProfileInfo(UserProfileGetDto userProfileGetDto, UserProfileInformation userProfileInformation) {
        userProfileGetDto.setAge(userProfileInformation.getAge());
        userProfileGetDto.setGender(userProfileInformation.getGender());
        userProfileGetDto.setHeight(userProfileInformation.getHeight());
        userProfileGetDto.setWeight(userProfileInformation.getWeight());
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
    public void setInitialAdminConfig(InitialAdminConfiguration initialAdminConfig) {
        this.initialAdminConfig = initialAdminConfig;
    }

    @Autowired
    public void setUserConfigurer(UserConfigurer userConfigurer) {
        this.userConfigurer = userConfigurer;
    }
}
