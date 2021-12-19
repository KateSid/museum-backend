package ru.ssau.labs.museumcatalogbackend.dao;

import org.springframework.data.repository.CrudRepository;
import ru.ssau.labs.museumcatalogbackend.domain.Restoration;

public interface RestorationDao extends CrudRepository<Restoration, Integer> {
}
