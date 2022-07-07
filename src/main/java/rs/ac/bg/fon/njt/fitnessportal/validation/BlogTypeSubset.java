package rs.ac.bg.fon.njt.fitnessportal.validation;

import rs.ac.bg.fon.njt.fitnessportal.entities.BlogType;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = BlogTypeSubSetValidator.class)
public @interface BlogTypeSubset {
    BlogType[] anyOf();
    String message() default "Blog must be one of these types: {anyOf}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
