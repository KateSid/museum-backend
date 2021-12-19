package ru.ssau.labs.museumcatalogbackend.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class EmployeeDto {

    private Long id;
    @NotBlank(message = "EmployeeNumber must be present.")
    private String employeeNumber;
    @NotBlank(message = "Full name must be present.")
    private String fullName;
    @NotBlank(message = "Position must be present.")
    private String position;
}
