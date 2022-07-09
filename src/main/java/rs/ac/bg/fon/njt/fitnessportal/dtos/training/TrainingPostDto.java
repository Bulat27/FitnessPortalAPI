package rs.ac.bg.fon.njt.fitnessportal.dtos.training;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

public class TrainingPostDto {

    @NotNull
    private LocalDate date;

    @NotNull
    private LocalTime startTime;

    @NotNull
    private LocalTime endTime;

    @NotNull
    private Integer maxSpots;

    public TrainingPostDto() { }

    public TrainingPostDto(LocalDate date, LocalTime startTime, LocalTime endTime, Integer maxSpots) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.maxSpots = maxSpots;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public Integer getMaxSpots() {
        return maxSpots;
    }

    public void setMaxSpots(Integer maxSpots) {
        this.maxSpots = maxSpots;
    }
}
