package ru.babich.planer.enity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(name = "task")
@NoArgsConstructor
@Data
@ToString
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "description") //описание особенностей и задач на смену
    private String description;

    @Column(name = "date", updatable = false)
    @JsonFormat( pattern = "dd-mm-yyyy")
    private LocalDateTime dateOfAdding;

    @ManyToOne()
    private WorkingPlace workingPlace;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @PrePersist
    private void creatingOfUser() {
        this.dateOfAdding = LocalDateTime.now();
    }

}
