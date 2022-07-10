package rs.ac.bg.fon.njt.fitnessportal.services;

import org.springframework.stereotype.Service;
import rs.ac.bg.fon.njt.fitnessportal.dtos.training.TrainingGetDto;
import rs.ac.bg.fon.njt.fitnessportal.dtos.training.TrainingPostDto;

import java.util.List;

@Service
public interface TrainingService {
    TrainingGetDto create(TrainingPostDto trainingPostDto, String userEmail);
    List<TrainingGetDto> getAvailableByCoachID(Integer coachID);
    void scheduleAppointment(Integer trainingID, String userEmail);
}
