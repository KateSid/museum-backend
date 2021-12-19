package ru.ssau.labs.museumcatalogbackend.dto;

import lombok.Getter;
import lombok.Setter;
import ru.ssau.labs.museumcatalogbackend.validator.CorrectDateTime;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@Setter
@CorrectDateTime.List({@CorrectDateTime(dateAfter = "startDate", dateBefore = "endDate")})
public class RestorationDto {

    private Integer id;
    private LocalDate startDate;
    private LocalDate endDate;
    @NotBlank(message = "Comment must be present.")
    private String comment;
    private ShortExhibitDto exhibit;
    private EmployeeDto employee;
}
