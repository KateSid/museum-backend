package ru.ssau.labs.museumcatalogbackend.dao;

import org.springframework.data.repository.CrudRepository;
import ru.ssau.labs.museumcatalogbackend.domain.Exposure;

public interface ExposureDao extends CrudRepository<Exposure, Integer> {
}
