package rs.ac.bg.fon.njt.fitnessportal.validation;

import rs.ac.bg.fon.njt.fitnessportal.entities.BlogType;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class BlogTypeSubSetValidator implements ConstraintValidator<BlogTypeSubset, BlogType> {

    private BlogType[] subset;

    @Override
    public void initialize(BlogTypeSubset constraintAnnotation) {
        this.subset = constraintAnnotation.anyOf();
    }

    @Override
    public boolean isValid(BlogType value, ConstraintValidatorContext constraintValidatorContext) {
        return value == null || Arrays.asList(subset).contains(value);
    }
}
