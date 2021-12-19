package ru.ssau.labs.museumcatalogbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ssau.labs.museumcatalogbackend.dao.RestorationDao;
import ru.ssau.labs.museumcatalogbackend.domain.Restoration;
import ru.ssau.labs.museumcatalogbackend.dto.RestorationDto;
import ru.ssau.labs.museumcatalogbackend.exception.EntityNotExistException;
import ru.ssau.labs.museumcatalogbackend.mapper.RestorationMapper;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class RestorationService {
    private final RestorationDao restorationDao;

    private final RestorationMapper restorationMapper;

    public RestorationDto save(RestorationDto restorationDto) {
        Restoration restoration = restorationMapper.toEntity(restorationDto);
        restoration = restorationDao.save(restoration);
        return restorationMapper.toDto(restoration);
    }

    public RestorationDto update(RestorationDto restorationDto) {
        if (restorationDao.existsById(restorationDto.getId())) {
            Restoration restoration = restorationDao.save(restorationMapper.toEntity(restorationDto));
            return restorationMapper.toDto(restoration);
        } else {
            throw new EntityNotExistException("Такой записи о реставрации не существует");
        }
    }

    public RestorationDto getRestoration(Integer id) {
        Restoration restoration = restorationDao.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Запись о реставрации не найдена"));
        return restorationMapper.toDto(restoration);
    }

    public void delete(Integer id) {
        restorationDao.deleteById(id);
    }

    public List<RestorationDto> getAll() {
        Iterable<Restoration> restoration = restorationDao.findAll();
        return StreamSupport.stream(restoration.spliterator(), false)
                .map(restorationMapper::toDto)
                .collect(Collectors.toList());
    }
}
