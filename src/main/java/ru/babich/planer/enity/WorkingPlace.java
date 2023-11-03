package ru.babich.planer.enity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "working_place",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "cons_working_place_name_unique",
                        columnNames = {"working_place_name"}
                )
        })
@NoArgsConstructor
@Data
@ToString

public class WorkingPlace {

    public static final String WORKING_PLACE_SEQ = "working_place_seq";
    @Id
    @SequenceGenerator(
            name = WORKING_PLACE_SEQ,
            sequenceName = WORKING_PLACE_SEQ,
            initialValue = 1,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = WORKING_PLACE_SEQ
    )
    private Long id;

    @Column(name = "working_place_name", nullable = false)
    private String workingPlaceName;

    @ManyToOne(targetEntity = User.class)
    private User user;

    @OneToMany(targetEntity = Task.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private List<Task> taskList;

}
