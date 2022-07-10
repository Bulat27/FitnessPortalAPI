package rs.ac.bg.fon.njt.fitnessportal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import rs.ac.bg.fon.njt.fitnessportal.dtos.user.UserGetDto;
import rs.ac.bg.fon.njt.fitnessportal.dtos.user.UserPostDto;
import rs.ac.bg.fon.njt.fitnessportal.dtos.user.UserPutDto;
import rs.ac.bg.fon.njt.fitnessportal.exception_handling.InvalidUserException;
import rs.ac.bg.fon.njt.fitnessportal.security.authorization.ApplicationUserRole;
import rs.ac.bg.fon.njt.fitnessportal.services.UserService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/api/v1/users")
public class UserController {

    private UserService userService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<UserGetDto>> get(){
        return ResponseEntity.ok(userService.get());
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserGetDto> get(@PathVariable String email, Authentication auth) {
        if(!isAdmin(auth) && !isLoggedInUser(auth, email)) throw new InvalidUserException();
        return ResponseEntity.ok(userService.get(email));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<UserGetDto> create(@RequestBody @Valid UserPostDto userPostDto){
        return ResponseEntity.ok(userService.create(userPostDto, List.of(ApplicationUserRole.ADMIN)));
    }

    @PutMapping
    public ResponseEntity<UserGetDto> update(@RequestBody @Valid UserPutDto userPutDto, Authentication auth){
        boolean isAdmin = isAdmin(auth);
        if(!isAdmin && !isLoggedInUser(auth, userPutDto.getEmail())) throw new InvalidUserException();

        if(!isAdmin && userPutDto.getRoles() != null) throw new IllegalArgumentException("User cannot update the roles");

        return ResponseEntity.ok(userService.update(userPutDto));
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> delete(@PathVariable String email, Authentication auth){
        if(!isAdmin(auth) && !isLoggedInUser(auth, email)) throw new InvalidUserException();

        userService.delete(email);
        return ResponseEntity.ok().build();
    }

    private boolean isAdmin(Authentication auth){
        return auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_" + ApplicationUserRole.ADMIN.name()));
    }

    private boolean isLoggedInUser(Authentication auth, String email){
        return email != null && email.equals(auth.getPrincipal());
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
