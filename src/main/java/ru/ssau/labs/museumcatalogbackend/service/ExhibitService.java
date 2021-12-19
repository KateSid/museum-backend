package ru.ssau.labs.museumcatalogbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ssau.labs.museumcatalogbackend.dao.ExhibitDao;
import ru.ssau.labs.museumcatalogbackend.domain.Exhibit;
import ru.ssau.labs.museumcatalogbackend.dto.ExhibitDto;
import ru.ssau.labs.museumcatalogbackend.exception.EntityNotExistException;
import ru.ssau.labs.museumcatalogbackend.mapper.ExhibitMapper;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class ExhibitService {
    private final ExhibitDao exhibitDao;

    private final ExhibitMapper exhibitMapper;

    public ExhibitDto save(ExhibitDto exhibitDto) {
        Exhibit exhibit = exhibitMapper.toEntity(exhibitDto);
        exhibit = exhibitDao.save(exhibit);
        return exhibitMapper.toDto(exhibit);
    }

    public ExhibitDto update(ExhibitDto exhibitDto) {
        if (exhibitDao.existsById(exhibitDto.getId())) {
            Exhibit exhibit = exhibitDao.save(exhibitMapper.toEntity(exhibitDto));
            return exhibitMapper.toDto(exhibit);
        } else {
            throw new EntityNotExistException("Такого экспоната не существует");
        }
    }

    public ExhibitDto getExhibit(Integer id) {
        Exhibit exhibit = exhibitDao.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Экспонат не найден"));
        return exhibitMapper.toDto(exhibit);
    }

    public void delete(Integer id) {
        exhibitDao.deleteById(id);
    }

    public List<ExhibitDto> getAll() {
        Iterable<Exhibit> exhibit = exhibitDao.findAll();
        return StreamSupport.stream(exhibit.spliterator(), false)
                .map(exhibitMapper::toDto)
                .collect(Collectors.toList());
    }
}
