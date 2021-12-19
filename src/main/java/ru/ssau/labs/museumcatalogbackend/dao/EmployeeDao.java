package ru.ssau.labs.museumcatalogbackend.dao;

import org.springframework.data.repository.CrudRepository;
import ru.ssau.labs.museumcatalogbackend.domain.Employee;

public interface  EmployeeDao extends CrudRepository<Employee, Long> {
}
