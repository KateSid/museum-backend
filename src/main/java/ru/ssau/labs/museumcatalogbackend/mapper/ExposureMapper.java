package ru.ssau.labs.museumcatalogbackend.mapper;

import org.mapstruct.Mapper;
import ru.ssau.labs.museumcatalogbackend.domain.Exposure;
import ru.ssau.labs.museumcatalogbackend.dto.ExposureDto;

@Mapper(componentModel = "spring")
public interface ExposureMapper {

    Exposure toEntity(ExposureDto dto);

    ExposureDto toDto(Exposure entity);
}
