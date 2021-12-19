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
import ru.ssau.labs.museumcatalogbackend.dto.ExhibitDto;
import ru.ssau.labs.museumcatalogbackend.service.ExhibitService;

import javax.validation.Valid;
import java.util.List;

@Validated
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/exhibits")
@RequiredArgsConstructor
public class ExhibitController {
    private final ExhibitService exhibitService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ExhibitDto save(@RequestBody @Valid ExhibitDto exhibitDto) {
        return exhibitService.save(exhibitDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ExhibitDto update(@RequestBody @Valid ExhibitDto exhibitDto, @PathVariable Integer id) {
        exhibitDto.setId(id);
        return exhibitService.update(exhibitDto);
    }

    @GetMapping(value = "/{id}")
    public ExhibitDto get(@PathVariable Integer id) {
        return exhibitService.getExhibit(id);
    }

    @GetMapping
    public List<ExhibitDto> getAll() {
        return exhibitService.getAll();
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete(@PathVariable Integer id) {
        exhibitService.delete(id);
    }
}
