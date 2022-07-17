package rs.ac.bg.fon.njt.fitnessportal.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Entity(name = "trainings")
public class Training {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private LocalTime startTime;

    @Column(nullable = false)
    private LocalTime endTime;

    @Column(nullable = false)
    private Integer maxSpots;

    @Column(nullable = false)
    private Integer remainingSpots;

    @ManyToOne
    @JoinColumn(name = "coach_id", nullable = false)
    private Coach coach;

//    @ManyToMany(mappedBy = "trainings")
//    private Set<Member> members; // TODO: Mozda ces morati da instanciras, mada ima logike da ce morati samo na owning strani.

    @OneToMany(mappedBy = "training", cascade = CascadeType.ALL)
    private Set<Appointment> appointments;// TODO: Mozda ce morati da se instancira

    public Training() { }

//    public Training(Integer id, LocalDate date, LocalTime startTime, LocalTime endTime, Integer maxSpots, Integer remainingSpots, Coach coach, Set<Member> members) {
//        this.id = id;
//        this.date = date;
//        this.startTime = startTime;
//        this.endTime = endTime;
//        this.maxSpots = maxSpots;
//        this.remainingSpots = remainingSpots;
//        this.coach = coach;
//        this.members = members;
//    }


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

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
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

//    public Set<Member> getMembers() {
//        return members;
//    }
//
//    public void setMembers(Set<Member> members) {
//        this.members = members;
//    }
    
    public Set<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(Set<Appointment> appointments) {
        this.appointments = appointments;
    }
}
