package ru.ssau.labs.museumcatalogbackend.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
public class ShowroomDto {

    private Integer id;
    @NotBlank
    private String name;
    @NotNull(message = "Floor must be not null.")
    private Integer floor;
    @NotNull
    @Positive(message = "Size must be greater that 0.")
    private Integer size;
}
