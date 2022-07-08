package rs.ac.bg.fon.njt.fitnessportal.dtos.coach;

import rs.ac.bg.fon.njt.fitnessportal.dtos.user.UserPostDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CoachPostDto extends UserPostDto {

    @NotNull
    private Integer yearsOfExperience;

    @NotBlank
    private String imageSrc;

    public CoachPostDto() {
        super();
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
}
