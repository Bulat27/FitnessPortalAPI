package rs.ac.bg.fon.njt.fitnessportal.entities;

import javax.persistence.*;

@Entity(name = "users_profile_information")
public class UserProfileInformation {

    @Id
    @GeneratedValue
    private Integer id;
    private Integer height;

    private Integer weight;
    private Integer age;
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    public UserProfileInformation() { }

    public UserProfileInformation(Integer height, Integer weight, Integer age, Gender gender) {
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.gender = gender;
    }
    public UserProfileInformation(Integer id, Integer height, Integer weight, Integer age, Gender gender, User user) {
        this.id = id;
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.gender = gender;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        if(user != null) user.setUserProfileInformation(this); //TODO: Mislim da ne mora, al dobro!
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
