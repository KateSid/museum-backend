package ru.ssau.labs.museumcatalogbackend.dto;

import lombok.Data;
import ru.ssau.labs.museumcatalogbackend.validator.CorrectDateTime;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Data
@CorrectDateTime.List({@CorrectDateTime(dateAfter = "startDateTime", dateBefore = "endDateTime")})
public class ExposureDto {

    private Integer id;
    @NotBlank(message = "Name must be present.")
    private String name;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    @NotBlank(message = "Type must be present.")
    private String type;
    private ShowroomDto showroom;
    private List<ShortExhibitDto> exhibits;
}
