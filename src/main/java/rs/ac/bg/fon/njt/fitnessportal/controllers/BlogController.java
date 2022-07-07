package rs.ac.bg.fon.njt.fitnessportal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import rs.ac.bg.fon.njt.fitnessportal.dtos.blog.BlogGetDto;
import rs.ac.bg.fon.njt.fitnessportal.dtos.blog.BlogPostDto;
import rs.ac.bg.fon.njt.fitnessportal.services.BlogService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("api/v1/blogs")
public class BlogController {

    private BlogService blogService;

    @GetMapping
    public ResponseEntity<List<BlogGetDto>> get(){
        return ResponseEntity.ok(blogService.get());
    }

    @PostMapping
    ResponseEntity<BlogGetDto> create(@RequestBody @Valid BlogPostDto blogPostDto, Authentication authentication){
        String userEmail = authentication.getPrincipal().toString();
        return new ResponseEntity<>(blogService.create(blogPostDto, userEmail), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id, Authentication authentication){
        blogService.delete(id, authentication.getPrincipal().toString());
        return ResponseEntity.ok().build();
    }



    @Autowired
    public void setBlogService(BlogService blogService) {
        this.blogService = blogService;
    }
}
