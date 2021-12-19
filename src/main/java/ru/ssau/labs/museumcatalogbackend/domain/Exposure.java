package ru.ssau.labs.museumcatalogbackend.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Exposure {

    private static final String ID_SEQ = "author_id_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = ID_SEQ)
    @SequenceGenerator(name = ID_SEQ, sequenceName = ID_SEQ, allocationSize = 1)
    private Integer id;
    private String name;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String type;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "showroom_id")
    private Showroom showroom;
    @ManyToMany
    @JoinTable(
            name="exhibit_exposure",
            joinColumns=@JoinColumn(name="exposure_id", referencedColumnName="id"),
            inverseJoinColumns=@JoinColumn(name="exhibit_id", referencedColumnName="id"))
    private List<Exhibit> exhibits;
}

