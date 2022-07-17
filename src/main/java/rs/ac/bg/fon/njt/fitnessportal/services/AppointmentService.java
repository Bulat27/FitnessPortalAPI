package rs.ac.bg.fon.njt.fitnessportal.services;

import org.springframework.stereotype.Service;
import rs.ac.bg.fon.njt.fitnessportal.dtos.appointment.AppointmentAttendancePutDto;
import rs.ac.bg.fon.njt.fitnessportal.dtos.appointment.AppointmentWithMemberGetDto;
import rs.ac.bg.fon.njt.fitnessportal.dtos.appointment.AppointmentWithTrainingGetDto;

import java.util.List;

@Service
public interface AppointmentService {
    List<AppointmentWithTrainingGetDto> getByMember(String memberEmail);
    void scheduleAppointment(Integer trainingID, String userEmail);
    List<AppointmentWithMemberGetDto> getByTraining(Integer trainingID, String coachEmail);
    AppointmentWithMemberGetDto updateAttendance(Integer appointmentID, AppointmentAttendancePutDto appointmentAttendancePutDto, String coachEmail);
}
