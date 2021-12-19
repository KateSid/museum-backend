package ru.ssau.labs.museumcatalogbackend.mapper;

import org.mapstruct.Mapper;
import ru.ssau.labs.museumcatalogbackend.domain.Author;
import ru.ssau.labs.museumcatalogbackend.dto.AuthorDto;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    Author toEntity(AuthorDto dto);

    AuthorDto toDto(Author entity);
}
