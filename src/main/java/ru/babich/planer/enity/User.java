package ru.babich.planer.enity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "employee", uniqueConstraints =
        {@UniqueConstraint(name = "cons_user_email_unique", columnNames = {"email"}),
        @UniqueConstraint(name ="cons_user_username_unique", columnNames = "username")})
@NoArgsConstructor
@Data
@ToString
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    @Max(message = "have to be not more than 30 symbols", value = 30)
    private String name;

    @Column(name = "surname", nullable = false)
    @Max(message = "have to be not more than 30 symbols", value = 30)
    private String surname;

    @Column(name = "username",updatable = false, unique = true, nullable = false)
    @Max(message = "have to be not more than 30 symbols", value = 30)
    private String username;

    @Column(name = "email", nullable = false, unique = true,updatable = false)
    @Max(message = "have to be not more than 50 symbols", value = 50)
    private String email;                                             //выступает в роли username для авторизации

    @Column(name = "password", nullable = false)
    @Max(message = "have to be not more than 30 symbols", value = 30)
    private String password;

    @Column(name = "date_of_creation", updatable = false)
    @JsonFormat(pattern = "dd-mm-yyyy")
    private LocalDateTime localDate;

    @Transient
    private Collection<? extends GrantedAuthority> authorities;

    @Column(name = "user_role", nullable = false)
    private String role;

    @OneToOne
    private Role userRole;

    @OneToMany(targetEntity = Task.class,
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            mappedBy = "user")
    private List<Task> taskList;


    @OneToMany(targetEntity = WorkingPlace.class,
            cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    private List<WorkingPlace> workingPlace;

    public User(String name, String surname,String username, String email, String password, LocalDateTime localDate, String role) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.email = email;
        this.password = password;
        this.localDate = localDate;
        this.role = role;

        userRole = new Role();
        setRole(role);
    }

    public User(Long id, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    @PrePersist
    private void creatingOfUser() {
        this.localDate = LocalDateTime.now();
    }


    /**
     * SECURITY PART
     */

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
