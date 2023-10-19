package ru.babich.planer.annotations;

import jakarta.validation.Constraint;
import ru.babich.planer.validations.PasswordMatcherValidator;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.FIELD,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordMatcherValidator.class)
@Documented
public @interface PasswordMatchers {

    String message() default "Password don`t match. Check it one more time";
}
