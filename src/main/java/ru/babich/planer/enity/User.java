package ru.babich.planer.enity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "user",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "cons_user_email_unique",
                        columnNames = {"name"}
                )
        })
@NoArgsConstructor
@Data
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    @Max(message = "have to be not more than 30 symbols",value = 30)
    private String name;

    @Column(name = "surname", nullable = false)
    @Max(message = "have to be not more than 30 symbols",value = 30)
    private String surname;

    @Column(name = "email", nullable = false, unique = true)
    @Max(message = "have to be not more than 50 symbols",value = 50)
    private String email;

    @Column(name = "password", nullable = false)
    @Max(message = "have to be not more than 30 symbols",value = 30)
    private String password;

    @Column(name = "date_of_creation",updatable = false)
    @JsonFormat(pattern = "dd-mm-yyyy")
    private LocalDateTime localDate;

    @OneToOne
    private Role role;

    @OneToMany(targetEntity = Task.class,
                fetch = FetchType.EAGER,
                cascade = CascadeType.ALL,
                mappedBy = "user")
    private List<Task> taskList;


    @OneToMany(targetEntity = WorkingPlace.class,
            cascade = {CascadeType.DETACH,
                        CascadeType.REFRESH})
    private List<WorkingPlace> workingPlace;

    public User(String name, String surname, String email, String password, LocalDateTime localDate, Role role) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.localDate = localDate;
        this.role = role;
    }

    @PrePersist
    private void creatingOfUser() {
        this.localDate = LocalDateTime.now();
    }


}
