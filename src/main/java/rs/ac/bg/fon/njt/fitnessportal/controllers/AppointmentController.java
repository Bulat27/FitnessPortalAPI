package rs.ac.bg.fon.njt.fitnessportal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import rs.ac.bg.fon.njt.fitnessportal.dtos.appointment.AppointmentGetDto;
import rs.ac.bg.fon.njt.fitnessportal.exception_handling.InvalidUserException;
import rs.ac.bg.fon.njt.fitnessportal.services.AppointmentService;

import java.util.List;

@Controller
@RequestMapping("api/v1/appointments")
public class AppointmentController {

    private AppointmentService appointmentService;

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/member/{email}")
    public ResponseEntity<List<AppointmentGetDto>> getByMember(@PathVariable String email, Authentication auth){
        if(!isLoggedInUser(auth, email)) throw new InvalidUserException();

        return ResponseEntity.ok(appointmentService.getByMember(email));
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/{trainingID}")
    public ResponseEntity<Void> scheduleAppointment(@PathVariable Integer trainingID, Authentication authentication){
        String userEmail = authentication.getPrincipal().toString();

        appointmentService.scheduleAppointment(trainingID, userEmail);

        return ResponseEntity.ok().build();
    }

    private boolean isLoggedInUser(Authentication auth, String email) {
        return email != null && email.equals(auth.getPrincipal());
    }

    @Autowired
    public void setAppointmentService(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }
}
