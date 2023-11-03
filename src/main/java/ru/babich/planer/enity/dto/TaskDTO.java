package ru.babich.planer.enity.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import ru.babich.planer.enity.WorkingPlace;

@Data
public class TaskDTO {

    private Long Id;

    @NotEmpty
    private String description;

    @NotEmpty
    private WorkingPlace workingPlace;
}
