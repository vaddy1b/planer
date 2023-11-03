package ru.babich.planer.payload.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import ru.babich.planer.annotations.PasswordMatchers;
import ru.babich.planer.annotations.ValidEmail;
import ru.babich.planer.annotations.ValidPassword;

@Data
@PasswordMatchers
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
    private String confirmedPassword;

    @NotEmpty(message = "Please choose your role")
    private String role = "CONTROLLER";

    @NotEmpty(message = "Please enter your name")
    private String name;

    @NotEmpty(message = "Please enter your lastname")
    private String surname;

    public SignUpRequest(String email, String username, String password, String confirmedPassword, String name, String surname) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.confirmedPassword = confirmedPassword;
        this.name = name;
        this.surname = surname;
    }
}
