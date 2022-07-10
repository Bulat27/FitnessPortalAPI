package rs.ac.bg.fon.njt.fitnessportal.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("Member")
public class Member extends User {

    @ManyToMany //TODO: Mozda ces morati da stavis EAGER ako te bude zajebavalo!
    @JoinTable(name = "appointments",
               joinColumns = @JoinColumn(name = "member_id"),
               inverseJoinColumns = @JoinColumn(name = "training_id"))
    private Set<Training> trainings  = new HashSet<>();// TODO: Mozda ce biti potrebno da ovo intanciras

    public Member() {
        super();
    }

    public Member(Integer id, String firstName, String lastName, String email, String password) {
        super(id, firstName, lastName, email, password);
    }

    public Set<Training> getTrainings() {
        return trainings;
    }

    public void setTrainings(Set<Training> trainings) {
        this.trainings = trainings;
    }

    public void addTraining(Training t){
        trainings.add(t);
    }
}
