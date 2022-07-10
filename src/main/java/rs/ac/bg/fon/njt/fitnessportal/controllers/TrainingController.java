package rs.ac.bg.fon.njt.fitnessportal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import rs.ac.bg.fon.njt.fitnessportal.dtos.training.TrainingGetDto;
import rs.ac.bg.fon.njt.fitnessportal.dtos.training.TrainingPostDto;
import rs.ac.bg.fon.njt.fitnessportal.exception_handling.InvalidUserException;
import rs.ac.bg.fon.njt.fitnessportal.services.TrainingService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("api/v1/trainings")
public class TrainingController {

    private TrainingService trainingService;

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/coach/{coachID}")
    public ResponseEntity<List<TrainingGetDto>> getAvailableByCoach(@PathVariable Integer coachID){
        return ResponseEntity.ok(trainingService.getAvailableByCoachID(coachID));
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/member/{email}")
    public ResponseEntity<List<TrainingGetDto>> getByMember(@PathVariable String email, Authentication auth){
        if(!isLoggedInUser(auth, email)) throw new InvalidUserException();

        return ResponseEntity.ok(trainingService.getByMember(email));
    }

    @PreAuthorize("hasRole('ROLE_COACH')")
    @PostMapping
    public ResponseEntity<TrainingGetDto> create(@RequestBody @Valid TrainingPostDto trainingPostDto, Authentication authentication){
        String userEmail = authentication.getPrincipal().toString();
        return new ResponseEntity<>(trainingService.create(trainingPostDto, userEmail), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("appointments/{trainingID}")
    public ResponseEntity<Void> scheduleAppointment(@PathVariable Integer trainingID, Authentication authentication){
        String userEmail = authentication.getPrincipal().toString();

        trainingService.scheduleAppointment(trainingID, userEmail);

        return ResponseEntity.ok().build();
    }

    private boolean isLoggedInUser(Authentication auth, String email) {
        return email != null && email.equals(auth.getPrincipal());
    }

    @Autowired
    public void setTrainingService(TrainingService trainingService) {
        this.trainingService = trainingService;
    }
}
