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
import ru.ssau.labs.museumcatalogbackend.dto.ExposureDto;
import ru.ssau.labs.museumcatalogbackend.service.ExposureService;

import javax.validation.Valid;
import java.util.List;

@Validated
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/exposures")
@RequiredArgsConstructor
public class ExposureController {
    private final ExposureService exposureService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public ExposureDto save(@RequestBody @Valid ExposureDto exposureDto) {
        return exposureService.save(exposureDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ExposureDto update(@RequestBody @Valid ExposureDto exposureDto, @PathVariable Integer id) {
        exposureDto.setId(id);
        return exposureService.update(exposureDto);
    }

    @GetMapping(value = "/{id}")
    public ExposureDto get(@PathVariable Integer id) {
        return exposureService.getExposure(id);
    }

    @GetMapping
    public List<ExposureDto> getAll() {
        return exposureService.getAll();
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete(@PathVariable Integer id) {
        exposureService.delete(id);
    }
}
