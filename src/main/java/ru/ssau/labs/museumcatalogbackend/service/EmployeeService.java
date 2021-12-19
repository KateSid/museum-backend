package ru.ssau.labs.museumcatalogbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ssau.labs.museumcatalogbackend.dao.EmployeeDao;
import ru.ssau.labs.museumcatalogbackend.domain.Employee;
import ru.ssau.labs.museumcatalogbackend.dto.EmployeeDto;
import ru.ssau.labs.museumcatalogbackend.exception.EntityNotExistException;
import ru.ssau.labs.museumcatalogbackend.mapper.EmployeeMapper;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeDao employeeDao;

    private final EmployeeMapper employeeMapper;

    public EmployeeDto save(EmployeeDto employeeDto) {
        Employee employee = employeeMapper.toEntity(employeeDto);
        employee = employeeDao.save(employee);
        return employeeMapper.toDto(employee);
    }

    public EmployeeDto update(EmployeeDto employeeDto) {
        if (employeeDao.existsById(employeeDto.getId())) {
            Employee employee = employeeDao.save(employeeMapper.toEntity(employeeDto));
            return employeeMapper.toDto(employee);
        } else {
            throw new EntityNotExistException("Такого сотрудника не существует");
        }
    }

    public EmployeeDto getEmployee(Long id) {
        Employee employee = employeeDao.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Сотрудник не найден"));
        return employeeMapper.toDto(employee);
    }

    public void delete(Long id) {
        employeeDao.deleteById(id);
    }

    public List<EmployeeDto> getAll() {
        Iterable<Employee> employee = employeeDao.findAll();
        return StreamSupport.stream(employee.spliterator(), false)
                .map(employeeMapper::toDto)
                .collect(Collectors.toList());
    }
}
