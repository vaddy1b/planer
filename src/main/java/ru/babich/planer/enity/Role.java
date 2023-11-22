package ru.babich.planer.enity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "role")
@Data
public class Role {

    public static final String ROLE_SEQ = "role_seq";
    public static final String DEF_ROLE = "CONTROLLER";

    @Id
    @SequenceGenerator(
            name = ROLE_SEQ,
            sequenceName = ROLE_SEQ,
            initialValue = 1,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = ROLE_SEQ
    )
    private Long id;

    @Column(name="role_name")
    private String roleName;

    @OneToOne
    private User user;
}