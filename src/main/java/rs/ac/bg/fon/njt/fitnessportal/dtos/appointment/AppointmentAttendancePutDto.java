package rs.ac.bg.fon.njt.fitnessportal.dtos.appointment;

import javax.validation.constraints.NotNull;

public class AppointmentAttendancePutDto {
    @NotNull
    private Boolean attended;

    public AppointmentAttendancePutDto() { }

    public AppointmentAttendancePutDto(Boolean attended) {
        this.attended = attended;
    }

    public Boolean getAttended() {
        return attended;
    }

    public void setAttended(Boolean attended) {
        this.attended = attended;
    }
}
