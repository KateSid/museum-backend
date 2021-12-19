package ru.ssau.labs.museumcatalogbackend.dto;

import lombok.Getter;
import lombok.Setter;
import ru.ssau.labs.museumcatalogbackend.domain.Exhibit;
import ru.ssau.labs.museumcatalogbackend.validator.CorrectDateTime;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@CorrectDateTime.List({@CorrectDateTime(dateAfter = "dateOfBirth", dateBefore = "dateOfDeath")})
public class AuthorDto {

    private Long id;
    private String firstName;
    private String lastName;
    @NotBlank(message = "Full name must be present.")
    private String fullName;
    @NotBlank(message = "Pseudonym must be present.")
    private String pseudonym;
    private LocalDate dateOfBirth;
    private LocalDate dateOfDeath;
    private String country;
}
