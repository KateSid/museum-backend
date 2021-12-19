package ru.ssau.labs.museumcatalogbackend.domain;

import lombok.Data;

@Data
public class ExhibitInfo {

    private String length;
    private String width;
    private String height;
    private String additionalInformation;
}
