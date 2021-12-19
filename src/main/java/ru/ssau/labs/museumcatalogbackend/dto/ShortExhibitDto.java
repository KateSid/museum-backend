package ru.ssau.labs.museumcatalogbackend.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class ShortExhibitDto {

    private Integer id;
    @NotBlank(message = "Name must be present.")
    private String name;
    @NotBlank(message = "Type must be present.")
    private String type;
    private LocalDate creationDate;
    @NotNull
    private ExhibitInfoDto info;
}
