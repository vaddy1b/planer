package ru.babich.planer.service;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.babich.planer.enity.Task;
import ru.babich.planer.enity.User;
import ru.babich.planer.exception.TaskAlreadyExistException;
import ru.babich.planer.exception.TaskNotFoundException;
import ru.babich.planer.repo.TaskRepository;
import ru.babich.planer.repo.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@Data
public class TaskService {

    public static final Logger LOG = LoggerFactory.getLogger(TaskService.class);
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private static List<Task> allTaskList;


    @Autowired
    public TaskService(UserRepository userRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }


    public List<Task> addToAllTaskList(Task task) throws TaskAlreadyExistException {

        if (!task.equals(null)) {

            try {
                LOG.info("Trying to add task in tasklist #{}", task.getId());
                for (Task taskToAdd : allTaskList) {
                    if (!allTaskList.contains(taskToAdd)) {
                        allTaskList.add(taskToAdd);
                    }
                }
            } catch (Exception e) {
                LOG.error("Can`t add this task, try another parameters", e.getMessage());
                throw new TaskAlreadyExistException("This task exist");
            }
            return allTaskList;
        }
        return null;
    }


    public List<Task> searchTasksOfExactWorker(Long id) throws TaskNotFoundException {

        try {
            LOG.info("Trying to find all user tasks with id {}", id);

            Optional<User> userById = userRepository.findUserById(id);
            List<Task> userTasks = userById.get().getTaskList();
            return userTasks;

        } catch (Exception e) {
            LOG.error("Cant find current user tasks by worker");
            throw new TaskNotFoundException("This user doesnt have this task");
        }
    }

}
