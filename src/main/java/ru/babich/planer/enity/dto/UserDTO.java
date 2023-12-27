package ru.babich.planer.enity.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import ru.babich.planer.enity.WorkingPlace;

import java.util.List;

@Data
public class UserDTO {

    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String surname;

    @NotEmpty
    private String bio;

    @NotEmpty
    private List<WorkingPlace> workingPlaceList;

    private String role;
}
