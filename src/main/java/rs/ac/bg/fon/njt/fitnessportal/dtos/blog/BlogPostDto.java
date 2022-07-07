package rs.ac.bg.fon.njt.fitnessportal.dtos.blog;

import rs.ac.bg.fon.njt.fitnessportal.entities.BlogType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class BlogPostDto {

    @NotBlank
    private String title;

    @NotBlank
    private String text;

    @NotNull
    private BlogType type;

    @NotNull
    private String imageSrc;

    public BlogPostDto() { }

    public BlogPostDto(String title, String text, BlogType type, String imageSrc) {
        this.title = title;
        this.text = text;
        this.type = type;
        this.imageSrc = imageSrc;
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

    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }
}
