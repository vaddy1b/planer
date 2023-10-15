package ru.babich.planer.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class InvalidLoginResponce {

    private  String username;

    private  String password;

    public InvalidLoginResponce(String username, String password) {
        this.username = "Invalid username";
        this.password = "Invalid password";
    }

}
