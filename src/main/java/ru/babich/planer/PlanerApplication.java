package ru.babich.planer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.babich.planer.enity.User;

import java.time.LocalDateTime;

@SpringBootApplication
public class PlanerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlanerApplication.class, args);
    }

}