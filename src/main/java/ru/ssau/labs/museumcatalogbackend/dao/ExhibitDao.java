package ru.ssau.labs.museumcatalogbackend.dao;

import org.springframework.data.repository.CrudRepository;
import ru.ssau.labs.museumcatalogbackend.domain.Exhibit;

public interface ExhibitDao extends CrudRepository<Exhibit, Integer> {
}
