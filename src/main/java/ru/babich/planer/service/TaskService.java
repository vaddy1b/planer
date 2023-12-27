package ru.babich.planer.service;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.babich.planer.enity.Task;
import ru.babich.planer.enity.User;
import ru.babich.planer.enity.WorkingPlace;
import ru.babich.planer.enity.dto.TaskDTO;
import ru.babich.planer.exception.*;
import ru.babich.planer.repo.TaskRepository;
import ru.babich.planer.repo.UserRepository;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.*;

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

    public Task createTask(TaskDTO dto, Principal principal) throws UnableToCreateTask {

        User user = getUserByPrincipal(principal);
        Task task = new Task();
        try {
            task.setUser(user);
            task.setDescription(dto.getDescription());
            task.setDateOfAdding(LocalDateTime.now());
            task.setWorkingPlace(dto.getWorkingPlace());

            List<Task> taskList = user.getTaskList();
            taskList.add(task);

            user.setTaskList(taskList);

            addToAllTaskList(task);

            return taskRepository.save(task);

        } catch (Exception e) {
            LOG.error("Unavailability to create task - {id}", dto.getId());
            throw new UnableToCreateTask("Unable to create task, try again later");
        } catch (TaskAlreadyExistException e) {
            LOG.error("Task has already exist with id", dto.getId());
            throw new RuntimeException(e);
        }
    }

    public void updateTask(TaskDTO dto) throws UnableToUpdateTaskException {
        //TODO прописать метод обновления цели/описания задачи

        try {
            Task updatedTask = new Task();

            updatedTask.setId(dto.getId());
            updatedTask.setWorkingPlace(dto.getWorkingPlace());
            updatedTask.setUser(dto.getUser());
            updatedTask.setDescription(dto.getDescription());

            taskRepository.save(updatedTask);
        } catch (Exception e) {
            LOG.error("Cant to update task with id # %d", dto.getId());
            throw new UnableToUpdateTaskException("Unable to update this task");
        }
    }

    public void deleteTask(Long id, Principal principal) throws TaskNotFoundException {

        try {
            Task task = taskRepository.findTaskById(id, principal);

            //проверка на то, не прошло ли больше 8 часов с момента добавления таски
            if (!task.getDateOfAdding().isBefore(task.getDateOfAdding().plusHours(8))) {
                taskRepository.delete(task);
            }
        } catch (Exception e) {
            LOG.error("Not found task in task repo");

            throw new TaskNotFoundException("Cant find task with id -" + id);
        }
    }

    public Map <String, List<Task>> tasksOfWorkingPlace(Principal principal) throws WorkingPlaceSearchException {

        Map<String, List<Task>> mapOfTasks = new HashMap<>();

        User user = getUserByPrincipal(principal);

        try {
            List<WorkingPlace> workingPlaceListOfUser = user.getWorkingPlaces();

            if (workingPlaceListOfUser.size() < 1) {
                WorkingPlace workingPlace = workingPlaceListOfUser.get(0);
                mapOfTasks.put(workingPlace.getWorkingPlaceName(), workingPlace.getTaskList());
                return mapOfTasks;
            }

            for (int i = 0; i < workingPlaceListOfUser.size() - 1; i++) {

                String nameOfWorkingPlace = workingPlaceListOfUser.get(i).getWorkingPlaceName();

                for (Task tasks : workingPlaceListOfUser.get(i).getTaskList()) {
                    mapOfTasks.put(nameOfWorkingPlace, workingPlaceListOfUser.get(i).getTaskList());
                }
            }

            return mapOfTasks;
        }catch (Exception e){
            LOG.error("Error with finding tasks by working place");
            throw new WorkingPlaceSearchException("Error of finding working place and connected tasks ");
        }
    }

    public List<Task> findAllTasksOfExactWorker(Principal principal) throws TaskNotFoundException {

        try {
            LOG.info("Trying to find all user tasks");

            User user = getUserByPrincipal(principal);

            List<Task> userTasks = user.getTaskList();
            return userTasks;

        } catch (Exception e) {
            LOG.error("Cant find current user tasks by worker");
            throw new TaskNotFoundException("This user doesnt have this task");
        }
    }

    public List<Task> getTaskByIdOfUser(Long id, Principal principal) {
        Optional<User> userById = userRepository.findUserById(id);

        List<Task> taskList = userById.get().getTaskList();

        return taskList;
    }


    public List<Task> addToAllTaskList(Task task) throws TaskAlreadyExistException {

        if (!task.equals(null)) {

            try {
                LOG.info("Trying to add task in task-list # %d", task.getId());
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

    public User getUserByPrincipal(Principal principal) {
        String username = principal.getName();
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
