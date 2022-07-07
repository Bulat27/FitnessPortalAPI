package rs.ac.bg.fon.njt.fitnessportal.mapstruct.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;
import rs.ac.bg.fon.njt.fitnessportal.dtos.blog.BlogGetDto;
import rs.ac.bg.fon.njt.fitnessportal.dtos.blog.BlogPostDto;
import rs.ac.bg.fon.njt.fitnessportal.entities.Blog;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface BlogMapper {
    BlogGetDto blogToBlogGetDto(Blog blog);
    List<BlogGetDto> blogToBlogGetDtos(List<Blog> blogs);
    Blog blogPostDtoToBlog(BlogPostDto blogPostDto);
}
