package ru.ssau.labs.museumcatalogbackend.domain;

import lombok.Getter;
import lombok.Setter;
import ru.ssau.labs.museumcatalogbackend.enums.RoleEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "usr")
public class User {
    private static final String ID_SEQ = "user_id_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = ID_SEQ)
    @SequenceGenerator(name = ID_SEQ, sequenceName = ID_SEQ, allocationSize = 1)
    private Long id;
    @Column(unique = true)
    private String login;
    private String password;
    @Enumerated(value = EnumType.STRING)
    private RoleEnum role;
}
