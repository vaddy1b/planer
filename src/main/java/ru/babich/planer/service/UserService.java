package ru.babich.planer.service;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.babich.planer.enity.Role;
import ru.babich.planer.enity.User;
import ru.babich.planer.enity.dto.UserDTO;
import ru.babich.planer.exception.UserExistException;
import ru.babich.planer.payload.request.SignUpRequest;
import ru.babich.planer.repo.UserRepository;

import java.security.Principal;

@Service
@Data
public class UserService {

    public static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(SignUpRequest userIn) throws UserExistException{
        User user = new User();

        user.setEmail(userIn.getEmail());
        user.setName(userIn.getName());
        user.setSurname(userIn.getSurname());
        user.setSurname(userIn.getUsername());
        user.setPassword(passwordEncoder.encode(userIn.getPassword()));
        user.setRole(Role.DEF_ROLE);

        try{
            LOG.info("Saving user {}", userIn.getEmail());
            return userRepository.save(user);
        } catch (Exception e){
            LOG.error("Can`t create this user, try another parameters", e.getMessage());
            throw new UserExistException("The user with email" +user.getEmail()+
                    " has already exist. Check  your credentials");
        }

    }


    public User updateUser(UserDTO dto, Principal principal){

        User user = getUser(principal);

        user.setId(dto.getId());
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());

        return userRepository.save(user);
    }

    public User getUser(Principal principal){
        return getUserByPrincipal(principal);
    }


    public User getUserByPrincipal(Principal principal){
        String username = principal.getName();
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
