package ru.ssau.labs.museumcatalogbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ssau.labs.museumcatalogbackend.dao.AuthorDao;
import ru.ssau.labs.museumcatalogbackend.domain.Author;
import ru.ssau.labs.museumcatalogbackend.dto.AuthorDto;
import ru.ssau.labs.museumcatalogbackend.exception.EntityNotExistException;
import ru.ssau.labs.museumcatalogbackend.mapper.AuthorMapper;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorDao authorDao;

    private final AuthorMapper authorMapper;

    public AuthorDto save(AuthorDto authorDto) {
        Author author = authorMapper.toEntity(authorDto);
        author = authorDao.save(author);
        return authorMapper.toDto(author);
    }

    public AuthorDto update(AuthorDto authorDto) {
        if (authorDao.existsById(authorDto.getId())) {
            Author author = authorDao.save(authorMapper.toEntity(authorDto));
            return authorMapper.toDto(author);
        } else {
            throw new EntityNotExistException("Такого автора не существует");
        }
    }

    public AuthorDto getAuthor(Long id) {
        Author author = authorDao.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Автор не найден"));
        return authorMapper.toDto(author);
    }

    public void delete(Long id) {
        authorDao.deleteById(id);
    }

    public List<AuthorDto> getAll() {
        Iterable<Author> author = authorDao.findAll();
        return StreamSupport.stream(author.spliterator(), false)
                .map(authorMapper::toDto)
                .collect(Collectors.toList());
    }
}
