package ru.babich.planer.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.babich.planer.enity.Task;
import ru.babich.planer.enity.User;
import ru.babich.planer.enity.WorkingPlace;

import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {

    Optional <Task> findByWorkingPlaceOrderByDateOfAdding(WorkingPlace workingPlace);

    Optional <Task> findByUser(User user);

    Optional <Task> findTaskByIdAndUser(Long id, User user);
    Optional <Task> findTaskByIdAndUser(Long id);

}
