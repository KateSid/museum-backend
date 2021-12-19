package ru.ssau.labs.museumcatalogbackend.mapper;

import org.mapstruct.Mapper;
import ru.ssau.labs.museumcatalogbackend.domain.Showroom;
import ru.ssau.labs.museumcatalogbackend.dto.ShowroomDto;

@Mapper(componentModel = "spring")
public interface ShowroomMapper {

    Showroom toEntity(ShowroomDto dto);

    ShowroomDto toDto(Showroom entity);
}
