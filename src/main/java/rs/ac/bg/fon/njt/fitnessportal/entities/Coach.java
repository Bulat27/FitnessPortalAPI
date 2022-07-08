package rs.ac.bg.fon.njt.fitnessportal.entities;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@DiscriminatorValue("Coach")
public class Coach extends User {

    private Integer yearsOfExperience;
    private String imageSrc;

    @OneToMany(mappedBy = "coach", cascade = CascadeType.ALL)
    private Set<Training> trainings; //TODO : Mozda ce morati da se instancira!

    public Coach() {
        super();
    }

    public Coach(Integer id, String firstName, String lastName, String email, String password, Integer yearsOfExperience, String imageSrc) {
        super(id, firstName, lastName, email, password);
        this.yearsOfExperience = yearsOfExperience;
        this.imageSrc = imageSrc;
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
}
