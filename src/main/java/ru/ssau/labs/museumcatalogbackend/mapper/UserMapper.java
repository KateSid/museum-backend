package ru.ssau.labs.museumcatalogbackend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.ssau.labs.museumcatalogbackend.domain.User;
import ru.ssau.labs.museumcatalogbackend.dto.UserDto;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mappings(@Mapping(source = "dto.username", target = "login"))
    User toEntity(UserDto dto);

    @Mappings(@Mapping(source = "entity.login", target = "username"))
    UserDto toDto(User entity);
}
