package ru.babich.planer.payload.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class InvalidLoginResponse {

    private  String username;

    private  String password;

    public InvalidLoginResponse(String username, String password) {
        this.username = "Invalid username";
        this.password = "Invalid password";
    }

}
