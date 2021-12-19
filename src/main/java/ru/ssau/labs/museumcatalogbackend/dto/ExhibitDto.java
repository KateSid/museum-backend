package ru.ssau.labs.museumcatalogbackend.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import ru.ssau.labs.museumcatalogbackend.domain.Author;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class ExhibitDto {

    private Integer id;
    @NotBlank(message = "Name must be present.")
    private String name;
    @NotBlank(message = "Type must be present.")
    private String type;
    private LocalDate creationDate;
    @NotNull
    private ExhibitInfoDto info;
    private Set<RestorationDto> restorations = new HashSet<>();
    private Set<AuthorDto> authors;
}
