package ru.ssau.labs.museumcatalogbackend.dto;

import lombok.Data;

@Data
public class ExhibitInfoDto {

    private String length;
    private String width;
    private String height;
    private String additionalInformation;
}
