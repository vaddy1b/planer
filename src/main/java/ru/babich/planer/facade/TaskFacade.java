package ru.babich.planer.facade;

import org.springframework.stereotype.Component;
import ru.babich.planer.enity.Task;
import ru.babich.planer.enity.dto.TaskDTO;

@Component
public class TaskFacade {

    public TaskDTO taskToDto (Task task){

        TaskDTO taskDTO = new TaskDTO();

        taskDTO.setId(task.getId());
        taskDTO.setUser(task.getUser());
        taskDTO.setWorkingPlace(task.getWorkingPlace());
        taskDTO.setDescription(task.getDescription());

        return taskDTO;
    }
}
