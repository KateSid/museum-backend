package ru.ssau.labs.museumcatalogbackend.domain;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class Exhibit {

    private static final String ID_SEQ = "exhibit_id_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = ID_SEQ)
    @SequenceGenerator(name = ID_SEQ, sequenceName = ID_SEQ, allocationSize = 1)
    private Integer id;
    private String name;
    private String type;
    private LocalDate creationDate;
    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private ExhibitInfo info;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "exhibit_id")
    private Set<Restoration> restorations = new HashSet<>();
    @ManyToMany
    @JoinTable(
            name="exhibit_exposure",
            joinColumns=@JoinColumn(name="exhibit_id", referencedColumnName="id"),
            inverseJoinColumns=@JoinColumn(name="exposure_id", referencedColumnName="id"))
    private Set<Exposure> exposures;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="author_exhibit",
            joinColumns=@JoinColumn(name="exhibit_id", referencedColumnName="id"),
            inverseJoinColumns=@JoinColumn(name="author_id", referencedColumnName="id"))
    private Set<Author> authors;
}
