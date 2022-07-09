package rs.ac.bg.fon.njt.fitnessportal.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@DiscriminatorValue("Coach")
public class Coach extends User {

    private Integer yearsOfExperience;
    private String imageSrc;

    @Column(columnDefinition = "TEXT")
    private String biography;

    @OneToMany(mappedBy = "coach", cascade = CascadeType.ALL)
    private Set<Training> trainings; //TODO : Mozda ce morati da se instancira!

    public Coach() {
        super();
    }

    public Coach(Integer id, String firstName, String lastName, String email, String password, Integer yearsOfExperience, String imageSrc, String biography) {
        super(id, firstName, lastName, email, password);
        this.yearsOfExperience = yearsOfExperience;
        this.imageSrc = imageSrc;
        this.biography = biography;
    }

    public Integer getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(Integer yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }

    public Set<Training> getAppointments() {
        return trainings;
    }

    public void setAppointments(Set<Training> trainings) {
        this.trainings = trainings;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public Set<Training> getTrainings() {
        return trainings;
    }

    public void setTrainings(Set<Training> trainings) {
        this.trainings = trainings;
    }
}
