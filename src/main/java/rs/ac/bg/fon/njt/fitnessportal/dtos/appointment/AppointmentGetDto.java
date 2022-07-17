package rs.ac.bg.fon.njt.fitnessportal.dtos.appointment;

import rs.ac.bg.fon.njt.fitnessportal.dtos.training.TrainingGetDto;

public class AppointmentGetDto {

    private Integer id;
    private Boolean attended;
    private TrainingGetDto training;

    public AppointmentGetDto() { }

    public AppointmentGetDto(Integer id, Boolean attended, TrainingGetDto training) {
        this.id = id;
        this.attended = attended;
        this.training = training;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getAttended() {
        return attended;
    }

    public void setAttended(Boolean attended) {
        this.attended = attended;
    }

    public TrainingGetDto getTraining() {
        return training;
    }

    public void setTraining(TrainingGetDto training) {
        this.training = training;
    }
}
