package rs.ac.bg.fon.njt.fitnessportal.dtos.user;

import rs.ac.bg.fon.njt.fitnessportal.entities.Gender;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class ProfilePutDto {

    private String firstName;
    private String lastName;
    private String password;
    @Max(250)
    @Min(100)
    private Integer height;
    @Max(300)
    @Min(20)
    private Integer weight;
    @Max(100)
    @Min(5)
    private Integer age;
    private Gender gender;

    public ProfilePutDto() { }

    public ProfilePutDto(String firstName, String lastName, String password, Integer height, Integer weight, Integer age, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.gender = gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
