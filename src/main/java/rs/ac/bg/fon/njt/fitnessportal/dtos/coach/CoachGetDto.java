package rs.ac.bg.fon.njt.fitnessportal.dtos.coach;

import rs.ac.bg.fon.njt.fitnessportal.dtos.user.UserGetDto;

public class CoachGetDto extends UserGetDto {

    private Integer yearsOfExperience;
    private String imageSrc;
    private String biography;

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

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }
}
