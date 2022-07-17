package rs.ac.bg.fon.njt.fitnessportal.mapstruct.mappers;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import rs.ac.bg.fon.njt.fitnessportal.dtos.appointment.AppointmentGetDto;
import rs.ac.bg.fon.njt.fitnessportal.entities.Appointment;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface AppointmentMapper {

    AppointmentGetDto appointmentToAppointmentGetDto(Appointment appointment);
    List<AppointmentGetDto> appointmentsToAppointmentGetDtos(List<Appointment> appointments);
}
