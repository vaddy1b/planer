package ru.babich.planer.payload.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import ru.babich.planer.annotations.PasswordMatchers;
import ru.babich.planer.annotations.ValidEmail;
import ru.babich.planer.annotations.ValidPassword;

@Data
public class SignUpRequest {


    @Email(message = "It should have email format")
    @NotBlank(message = "Email is required")
    @ValidEmail
    private String email;

    @NotEmpty(message = "Please enter your username")
    private String username;

    @NotEmpty(message = "Please enter your password")
    @ValidPassword
    private String password;

    @NotEmpty(message = "Repeat the password")
    @PasswordMatchers
    private String confirmedPassword;

    @NotEmpty(message = "Please enter your name")
    private String name;

    @NotEmpty(message = "Please enter your lastname")
    private String lastname;


}
