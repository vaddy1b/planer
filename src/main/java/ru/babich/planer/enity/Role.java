package ru.babich.planer.enity;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role {

    public static final String ROLE_SEQ = "role_seq";

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

    @Column(name="name")
    private String roleName;

    @OneToOne
    private User user;
}