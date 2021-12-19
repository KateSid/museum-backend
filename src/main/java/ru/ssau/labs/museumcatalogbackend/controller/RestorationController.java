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
import ru.ssau.labs.museumcatalogbackend.dto.RestorationDto;
import ru.ssau.labs.museumcatalogbackend.service.RestorationService;

import javax.validation.Valid;
import java.util.List;

@Validated
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/restorations")
@RequiredArgsConstructor
public class RestorationController {
    private final RestorationService restorationService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public RestorationDto save(@RequestBody @Valid RestorationDto restorationDto) {
        return restorationService.save(restorationDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public RestorationDto update(@RequestBody @Valid RestorationDto restorationDto, @PathVariable Integer id) {
        restorationDto.setId(id);
        return restorationService.update(restorationDto);
    }

    @GetMapping(value = "/{id}")
    public RestorationDto get(@PathVariable Integer id) {
        return restorationService.getRestoration(id);
    }

    @GetMapping
    public List<RestorationDto> getAll() {
        return restorationService.getAll();
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete(@PathVariable Integer id) {
        restorationService.delete(id);
    }
}
