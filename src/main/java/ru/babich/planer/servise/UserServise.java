package ru.babich.planer.servise;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.babich.planer.enity.User;
import ru.babich.planer.exception.UserExistException;
import ru.babich.planer.payload.request.SignUpRequest;
import ru.babich.planer.repo.UserRepository;

@Service
@Data
public class UserServise {

    public static final Logger LOG = LoggerFactory.getLogger(UserServise.class);

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServise(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(SignUpRequest userIn) throws UserExistException{
        User user = new User();

        user.setEmail(userIn.getEmail());
        user.setName(userIn.getName());
        user.setSurname(userIn.getUsername());
        user.setPassword(passwordEncoder.encode(userIn.getPassword()));
        user.setRole(user.getRole().equals("ADMIN") ? "ADMIN" : "CONTROLLER ");

        try{
            LOG.info("Saving user {}", userIn.getEmail());
            return userRepository.save(user);
        } catch (Exception e){
            LOG.error("Can`t create this user, try another parameters", e.getMessage());
            throw new UserExistException("The user with email" +user.getEmail()+
                    " has already exist. Check your credentials");
        }

    }
}
