package rs.ac.bg.fon.njt.fitnessportal.mapstruct.mappers;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import rs.ac.bg.fon.njt.fitnessportal.dtos.appointment.AppointmentWithMemberGetDto;
import rs.ac.bg.fon.njt.fitnessportal.dtos.appointment.AppointmentWithTrainingGetDto;
import rs.ac.bg.fon.njt.fitnessportal.entities.Appointment;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface AppointmentMapper {

    AppointmentWithTrainingGetDto appointmentToAppointmentWithTrainingGetDto(Appointment appointment);
    List<AppointmentWithTrainingGetDto> appointmentsToAppointmentWithTrainingGetDtos(List<Appointment> appointments);
    AppointmentWithMemberGetDto appointmentToAppointmentWithMemberGetDto(Appointment appointment);
    List<AppointmentWithMemberGetDto> appointmentsToAppointmentWithMemberGetDtos(List<Appointment> appointments);

}
