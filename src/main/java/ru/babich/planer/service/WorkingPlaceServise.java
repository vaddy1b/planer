package ru.babich.planer.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.babich.planer.enity.User;
import ru.babich.planer.enity.WorkingPlace;
import ru.babich.planer.repo.TaskRepository;
import ru.babich.planer.repo.UserRepository;

import java.security.Principal;
import java.util.List;

@Service
@Data
public class WorkingPlaceServise {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    @Autowired
    public WorkingPlaceServise(UserRepository userRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    public List<WorkingPlace> getWorkingPlacesByUser(Long id) {
        List <WorkingPlace> list = userRepository.findUserById(id).get().getWorkingPlaces();
        return list;
    }

    public User getUserByPrincipal (Principal principal)
    {
        String username = principal.getName();
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Can't to find user with username - " + username));
    }
}
