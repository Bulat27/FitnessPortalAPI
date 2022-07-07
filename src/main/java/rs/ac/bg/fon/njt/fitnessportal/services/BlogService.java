package rs.ac.bg.fon.njt.fitnessportal.services;

import org.springframework.stereotype.Service;
import rs.ac.bg.fon.njt.fitnessportal.dtos.blog.BlogGetDto;
import rs.ac.bg.fon.njt.fitnessportal.dtos.blog.BlogPostDto;

import java.util.List;

@Service
public interface BlogService {
    List<BlogGetDto> get();
    BlogGetDto create(BlogPostDto blogPostDto, String userEmail);
    void delete(int id, String userEmail);
}
