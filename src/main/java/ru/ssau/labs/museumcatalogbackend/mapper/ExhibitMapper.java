package ru.ssau.labs.museumcatalogbackend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.ssau.labs.museumcatalogbackend.domain.Employee;
import ru.ssau.labs.museumcatalogbackend.domain.Exhibit;
import ru.ssau.labs.museumcatalogbackend.domain.Restoration;
import ru.ssau.labs.museumcatalogbackend.dto.EmployeeDto;
import ru.ssau.labs.museumcatalogbackend.dto.ExhibitDto;
import ru.ssau.labs.museumcatalogbackend.dto.RestorationDto;

import java.util.HashSet;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface ExhibitMapper {

    Exhibit toEntity(ExhibitDto dto);

    ExhibitDto toDto(Exhibit entity);

    default RestorationDto restorationToRestorationDto(Restoration restoration) {
        if ( restoration == null ) {
            return null;
        }

        RestorationDto restorationDto = new RestorationDto();

        restorationDto.setId( restoration.getId() );
        restorationDto.setStartDate( restoration.getStartDate() );
        restorationDto.setEndDate( restoration.getEndDate() );
        restorationDto.setComment( restoration.getComment() );
        restorationDto.setEmployee( employeeToEmployeeDto(restoration.getEmployee()) );

        return restorationDto;
    }

    default Set<RestorationDto> restorationSetToRestorationDtoSet(Set<Restoration> set) {
        if ( set == null ) {
            return null;
        }

        Set<RestorationDto> set1 = new HashSet<RestorationDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Restoration restoration : set ) {
            set1.add( restorationToRestorationDto( restoration ) );
        }

        return set1;
    }

    default EmployeeDto employeeToEmployeeDto(Employee employee) {
        if ( employee == null ) {
            return null;
        }

        EmployeeDto employeeDto = new EmployeeDto();

        employeeDto.setId( employee.getId() );
        employeeDto.setEmployeeNumber( employee.getEmployeeNumber() );
        employeeDto.setFullName( employee.getFullName() );
        employeeDto.setPosition( employee.getPosition() );

        return employeeDto;
    }
}
