package ru.ssau.labs.museumcatalogbackend.mapper;

import org.mapstruct.Mapper;
import ru.ssau.labs.museumcatalogbackend.domain.Employee;
import ru.ssau.labs.museumcatalogbackend.dto.EmployeeDto;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    Employee toEntity(EmployeeDto dto);

    EmployeeDto toDto(Employee entity);
}
