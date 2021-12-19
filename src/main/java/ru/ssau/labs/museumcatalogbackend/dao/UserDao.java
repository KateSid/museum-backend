package ru.ssau.labs.museumcatalogbackend.dao;

import org.springframework.data.repository.CrudRepository;
import ru.ssau.labs.museumcatalogbackend.domain.User;

import java.util.Optional;

public interface UserDao extends CrudRepository<User, Long> {
    Optional<User> findByLogin(String login);
}
