package ru.babich.planer.enity.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import ru.babich.planer.enity.Task;

import java.util.List;

@Data
public class WorkingPlaceDTO {

    private Long id;

    @NotEmpty
    private String workingPlaceName;

    @NotEmpty
    private List<Task> taskList;



}
