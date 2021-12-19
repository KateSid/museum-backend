package ru.ssau.labs.museumcatalogbackend.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
public class Author {

    private static final String ID_SEQ = "author_id_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = ID_SEQ)
    @SequenceGenerator(name = ID_SEQ, sequenceName = ID_SEQ, allocationSize = 1)
    private Long id;
    private String firstName;
    private String lastName;
    private String fullName;
    private String pseudonym;
    private LocalDate dateOfBirth;
    private LocalDate dateOfDeath;
    private String country;
    @ManyToMany
    @JoinTable(
            name="author_exhibit",
            joinColumns=@JoinColumn(name="author_id", referencedColumnName="id"),
            inverseJoinColumns=@JoinColumn(name="exhibit_id", referencedColumnName="id"))
    private List<Exhibit> exhibits;
}
