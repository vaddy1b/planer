package ru.babich.planer.validations;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ru.babich.planer.annotations.ValidEmail;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator implements ConstraintValidator<ValidEmail, String> {

    private static String EMAIL_REG = "^[^,№:;*()/<>\"\\ ]+@[A-Za-z0-9.-]+.[A-Za-z]{3}$";

    @Override
    public void initialize(ValidEmail constraintAnnotation) {

        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {

        return validateEmail(s);
    }

    private boolean validateEmail(String email){
        Pattern pattern = Pattern.compile(EMAIL_REG);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
