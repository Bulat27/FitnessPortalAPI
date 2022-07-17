package rs.ac.bg.fon.njt.fitnessportal.entities;

import javax.persistence.*;

@Entity(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue
    @Column(name = "appointment_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "training_id", nullable = false)
    private Training training;

    private Boolean attended;

    public Appointment() { }

    public Appointment(Member member, Training training) {
        this.member = member;
        this.training = training;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }

    public Boolean getAttended() {
        return attended;
    }

    public void setAttended(Boolean attended) {
        this.attended = attended;
    }
}
