package ru.babich.planer.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ru.babich.planer.annotations.PasswordMatchers;
import ru.babich.planer.payload.request.SignUpRequest;

public class PasswordMatcherValidator implements ConstraintValidator<PasswordMatchers, Object> {

    @Override
    public void initialize(PasswordMatchers constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext constraintValidatorContext) {
        SignUpRequest signUpRequest = (SignUpRequest) obj;

        return signUpRequest.getPassword().equals(((SignUpRequest) obj).getConfirmedPassword());

    }
}
