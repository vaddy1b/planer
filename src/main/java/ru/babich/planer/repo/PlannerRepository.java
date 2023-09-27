package ru.babich.planer.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.babich.planer.enity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlannerRepository extends JpaRepository<User,Long> {

    @Override
    List<User> findAll();

    @Override
    Optional<User> findById(Long aLong);

    @Override
    void delete(User entity);
}
