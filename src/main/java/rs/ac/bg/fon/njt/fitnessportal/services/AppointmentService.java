package rs.ac.bg.fon.njt.fitnessportal.services;

import org.springframework.stereotype.Service;
import rs.ac.bg.fon.njt.fitnessportal.dtos.appointment.AppointmentGetDto;

import java.util.List;

@Service
public interface AppointmentService {
    List<AppointmentGetDto> getByMember(String memberEmail);
    void scheduleAppointment(Integer trainingID, String userEmail);
}
