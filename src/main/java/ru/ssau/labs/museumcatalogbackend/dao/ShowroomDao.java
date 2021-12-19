package ru.ssau.labs.museumcatalogbackend.dao;

import org.springframework.data.repository.CrudRepository;
import ru.ssau.labs.museumcatalogbackend.domain.Showroom;

public interface ShowroomDao extends CrudRepository<Showroom, Integer> {
}
