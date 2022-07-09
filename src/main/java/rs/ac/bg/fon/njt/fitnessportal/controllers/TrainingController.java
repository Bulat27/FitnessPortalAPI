package rs.ac.bg.fon.njt.fitnessportal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import rs.ac.bg.fon.njt.fitnessportal.dtos.training.TrainingGetDto;
import rs.ac.bg.fon.njt.fitnessportal.dtos.training.TrainingPostDto;
import rs.ac.bg.fon.njt.fitnessportal.services.TrainingService;

import javax.validation.Valid;

@Controller
@RequestMapping("api/v1/trainings")
public class TrainingController {

    private TrainingService trainingService;

    @PreAuthorize("hasRole('ROLE_COACH')")
    @PostMapping
    public ResponseEntity<TrainingGetDto> create(@RequestBody @Valid TrainingPostDto trainingPostDto, Authentication authentication){
        String userEmail = authentication.getPrincipal().toString();
        return new ResponseEntity<>(trainingService.create(trainingPostDto, userEmail), HttpStatus.CREATED);
    }

    @Autowired
    public void setTrainingService(TrainingService trainingService) {
        this.trainingService = trainingService;
    }
}
