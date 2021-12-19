package ru.ssau.labs.museumcatalogbackend.mapper;

import org.mapstruct.Mapper;
import ru.ssau.labs.museumcatalogbackend.domain.Restoration;
import ru.ssau.labs.museumcatalogbackend.dto.RestorationDto;

@Mapper(componentModel = "spring")
public interface RestorationMapper {

    Restoration toEntity(RestorationDto dto);

    RestorationDto toDto(Restoration entity);
}
