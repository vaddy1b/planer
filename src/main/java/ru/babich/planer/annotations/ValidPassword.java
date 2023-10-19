package ru.babich.planer.annotations;

import jakarta.validation.constraints.Pattern;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.FIELD,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Pattern(regexp = "^(?=.*[/&$@%№!])(?=.*\\\\d)(?!.*[():;_\\\\s])[^():;_\\\\s]{6,50}$")
public @interface ValidPassword {

    String message() default "Password doesn`t equal to format";
}
