package ru.babich.planer.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.babich.planer.enity.User;
import ru.babich.planer.repo.TaskRepository;
import ru.babich.planer.repo.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsServise implements UserDetailsService {

    private UserRepository userRepository;
    private TaskRepository taskRepository;

    public CustomUserDetailsServise(UserRepository userRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findUserByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("These username doesn`t exist"));
        return build(user);
    }

    public User loadUserById(Long id){
        return userRepository.findUserById(id).orElse(null);
    }

    public static User build(User user) {

        List <GrantedAuthority> authorities = user.getAuthorities()
                .stream()
                .map(role -> new SimpleGrantedAuthority(user.getRole()))
                .collect(Collectors.toList());

        return new User(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                authorities
        );
    }

}
