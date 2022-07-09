package rs.ac.bg.fon.njt.fitnessportal.dtos.training;

import rs.ac.bg.fon.njt.fitnessportal.dtos.coach.CoachGetDto;

import java.time.LocalDate;
import java.time.LocalTime;

public class TrainingGetDto {

    private Integer id;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private Integer maxSpots;
    private Integer remainingSpots;
    private CoachGetDto coach;

    public TrainingGetDto() { }

    public TrainingGetDto(Integer id, LocalDate date, LocalTime startTime, LocalTime endTime, Integer maxSpots, Integer remainingSpots, CoachGetDto coach) {
        this.id = id;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.maxSpots = maxSpots;
        this.remainingSpots = remainingSpots;
        this.coach = coach;
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

    public CoachGetDto getCoach() {
        return coach;
    }

    public void setCoach(CoachGetDto coach) {
        this.coach = coach;
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
