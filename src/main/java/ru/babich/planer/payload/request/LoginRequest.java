package ru.babich.planer.payload.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoginRequest {

    @NotEmpty(message = "Username field should be filled up")
    private String username;

    @NotEmpty(message = "Password field should be filled up")
    private String password;
}
