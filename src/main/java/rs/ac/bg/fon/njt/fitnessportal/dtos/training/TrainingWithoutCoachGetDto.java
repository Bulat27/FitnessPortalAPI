package rs.ac.bg.fon.njt.fitnessportal.dtos.training;

import rs.ac.bg.fon.njt.fitnessportal.dtos.coach.CoachGetDto;

import java.time.LocalDate;
import java.time.LocalTime;

public class TrainingWithoutCoachGetDto {

    private Integer id;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private Integer maxSpots;
    private Integer remainingSpots;

    public TrainingWithoutCoachGetDto() { }

    public TrainingWithoutCoachGetDto(Integer id, LocalDate date, LocalTime startTime, LocalTime endTime, Integer maxSpots, Integer remainingSpots) {
        this.id = id;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.maxSpots = maxSpots;
        this.remainingSpots = remainingSpots;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getRemainingSpots() {
        return remainingSpots;
    }

    public void setRemainingSpots(Integer remainingSpots) {
        this.remainingSpots = remainingSpots;
    }
}
