package ru.ssau.labs.museumcatalogbackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.ssau.labs.museumcatalogbackend.dto.AuthorDto;
import ru.ssau.labs.museumcatalogbackend.service.AuthorService;

import javax.validation.Valid;
import java.util.List;

@Validated
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/authors")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public AuthorDto save(@RequestBody @Valid AuthorDto employeeDto) {
        return authorService.save(employeeDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public AuthorDto update(@RequestBody @Valid AuthorDto employeeDto, @PathVariable Long id) {
        employeeDto.setId(id);
        return authorService.update(employeeDto);
    }

    @GetMapping(value = "/{id}")
    public AuthorDto get(@PathVariable Long id) {
        return authorService.getAuthor(id);
    }

    @GetMapping
    public List<AuthorDto> getAll() {
        return authorService.getAll();
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete(@PathVariable Long id) {
        authorService.delete(id);
    }
}
