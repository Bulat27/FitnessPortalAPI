package rs.ac.bg.fon.njt.fitnessportal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import rs.ac.bg.fon.njt.fitnessportal.dtos.coach.CoachGetDto;
import rs.ac.bg.fon.njt.fitnessportal.dtos.coach.CoachPostDto;
import rs.ac.bg.fon.njt.fitnessportal.security.authorization.ApplicationUserRole;
import rs.ac.bg.fon.njt.fitnessportal.services.CoachService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("api/v1/coaches")
public class CoachController {

    private CoachService coachService;

    @GetMapping
    public ResponseEntity<List<CoachGetDto>> get(){
        return ResponseEntity.ok(coachService.get());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<CoachGetDto> create(@RequestBody @Valid CoachPostDto coachPostDto){
        return ResponseEntity.ok(coachService.create(coachPostDto, List.of(ApplicationUserRole.COACH)));
    }

    @Autowired
    public void setCoachService(CoachService coachService) {
        this.coachService = coachService;
    }
}
