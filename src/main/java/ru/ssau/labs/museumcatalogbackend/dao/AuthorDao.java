package ru.ssau.labs.museumcatalogbackend.dao;

import org.springframework.data.repository.CrudRepository;
import ru.ssau.labs.museumcatalogbackend.domain.Author;

public interface AuthorDao extends CrudRepository<Author, Long> {
}
