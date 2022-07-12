package rs.ac.bg.fon.njt.fitnessportal.services;

import org.springframework.stereotype.Service;
import rs.ac.bg.fon.njt.fitnessportal.dtos.user.*;
import rs.ac.bg.fon.njt.fitnessportal.security.authorization.ApplicationUserRole;

import java.util.List;

@Service
public interface UserService {

    List<UserGetDto> get();
    UserGetDto get(String email);
    UserGetDto create(UserPostDto userPostDto, List<ApplicationUserRole> roleTypes);
    void delete(String email);
    UserGetDto update(UserPutDto userPutDto);
    UserProfileGetDto getWithProfile(String email);
    UserProfileGetDto updateWithProfile(String email, ProfilePutDto profilePutDto);
}
