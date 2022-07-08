package rs.ac.bg.fon.njt.fitnessportal.services;

import org.springframework.stereotype.Service;
import rs.ac.bg.fon.njt.fitnessportal.dtos.coach.CoachGetDto;
import rs.ac.bg.fon.njt.fitnessportal.dtos.coach.CoachPostDto;
import rs.ac.bg.fon.njt.fitnessportal.security.authorization.ApplicationUserRole;

import java.util.List;

@Service
public interface CoachService {

    List<CoachGetDto> get();
    CoachGetDto create(CoachPostDto coachPostDto, List<ApplicationUserRole> roleTypes);
}
