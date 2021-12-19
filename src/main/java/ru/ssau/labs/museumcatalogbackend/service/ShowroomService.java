package ru.ssau.labs.museumcatalogbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ssau.labs.museumcatalogbackend.dao.ShowroomDao;
import ru.ssau.labs.museumcatalogbackend.domain.Showroom;
import ru.ssau.labs.museumcatalogbackend.dto.ShowroomDto;
import ru.ssau.labs.museumcatalogbackend.exception.EntityNotExistException;
import ru.ssau.labs.museumcatalogbackend.mapper.ShowroomMapper;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class ShowroomService {
    private final ShowroomDao showroomDao;

    private final ShowroomMapper showroomMapper;

    public ShowroomDto save(ShowroomDto showroomDto) {
        Showroom showroom = showroomMapper.toEntity(showroomDto);
        showroom = showroomDao.save(showroom);
        return showroomMapper.toDto(showroom);
    }

    public ShowroomDto update(ShowroomDto showroomDto) {
        if (showroomDao.existsById(showroomDto.getId())) {
            Showroom showroom = showroomDao.save(showroomMapper.toEntity(showroomDto));
            return showroomMapper.toDto(showroom);
        } else {
            throw new EntityNotExistException("Такого выставочного зала не существует");
        }
    }

    public ShowroomDto getShowroom(Integer id) {
        Showroom showroom = showroomDao.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Выставочный зал не найден"));
        return showroomMapper.toDto(showroom);
    }

    public void delete(Integer id) {
        showroomDao.deleteById(id);
    }

    public List<ShowroomDto> getAll() {
        Iterable<Showroom> showroom = showroomDao.findAll();
        return StreamSupport.stream(showroom.spliterator(), false)
                .map(showroomMapper::toDto)
                .collect(Collectors.toList());
    }
}
