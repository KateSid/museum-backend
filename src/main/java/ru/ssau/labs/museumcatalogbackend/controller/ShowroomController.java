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
import ru.ssau.labs.museumcatalogbackend.dto.ShowroomDto;
import ru.ssau.labs.museumcatalogbackend.service.ShowroomService;

import javax.validation.Valid;
import java.util.List;

@Validated
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/showrooms")
@RequiredArgsConstructor
public class ShowroomController {
    private final ShowroomService showroomService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public ShowroomDto save(@RequestBody @Valid ShowroomDto showroomDto) {
        return showroomService.save(showroomDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ShowroomDto update(@RequestBody @Valid ShowroomDto showroomDto, @PathVariable Integer id) {
        showroomDto.setId(id);
        return showroomService.update(showroomDto);
    }

    @GetMapping(value = "/{id}")
    public ShowroomDto get(@PathVariable Integer id) {
        return showroomService.getShowroom(id);
    }

    @GetMapping
    public List<ShowroomDto> getAll() {
        return showroomService.getAll();
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete(@PathVariable Integer id) {
        showroomService.delete(id);
    }
}
