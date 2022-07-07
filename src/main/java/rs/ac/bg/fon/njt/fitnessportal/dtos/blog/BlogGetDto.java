package rs.ac.bg.fon.njt.fitnessportal.dtos.blog;

import rs.ac.bg.fon.njt.fitnessportal.dtos.user.UserGetDto;
import rs.ac.bg.fon.njt.fitnessportal.entities.BlogType;

public class BlogGetDto {

    private Integer id;
    private String title;
    private String text;
    private String imageSrc;
    private BlogType type;
    private UserGetDto user;

    public BlogGetDto() { }

    public BlogGetDto(Integer id, String title, String text, String imageSrc, BlogType type, UserGetDto user) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.imageSrc = imageSrc;
        this.type = type;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public BlogType getType() {
        return type;
    }

    public void setType(BlogType type) {
        this.type = type;
    }

    public UserGetDto getUser() {
        return user;
    }

    public void setUser(UserGetDto user) {
        this.user = user;
    }

    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }
}
