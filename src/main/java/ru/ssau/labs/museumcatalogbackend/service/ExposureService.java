package ru.ssau.labs.museumcatalogbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ssau.labs.museumcatalogbackend.dao.ExposureDao;
import ru.ssau.labs.museumcatalogbackend.domain.Exposure;
import ru.ssau.labs.museumcatalogbackend.dto.ExposureDto;
import ru.ssau.labs.museumcatalogbackend.exception.EntityNotExistException;
import ru.ssau.labs.museumcatalogbackend.mapper.ExposureMapper;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class ExposureService {
    private final ExposureDao exposureDao;

    private final ExposureMapper exposureMapper;

    public ExposureDto save(ExposureDto exposureDto) {
        Exposure exposure = exposureMapper.toEntity(exposureDto);
        exposure = exposureDao.save(exposure);
        return exposureMapper.toDto(exposure);
    }

    public ExposureDto update(ExposureDto exposureDto) {
        if (exposureDao.existsById(exposureDto.getId())) {
            Exposure exposure = exposureDao.save(exposureMapper.toEntity(exposureDto));
            return exposureMapper.toDto(exposure);
        } else {
            throw new EntityNotExistException("Такой экзпозиции не существует");
        }
    }

    public ExposureDto getExposure(Integer id) {
        Exposure exposure = exposureDao.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Экспозиция не найден"));
        return exposureMapper.toDto(exposure);
    }

    public void delete(Integer id) {
        exposureDao.deleteById(id);
    }

    public List<ExposureDto> getAll() {
        Iterable<Exposure> exposure = exposureDao.findAll();
        return StreamSupport.stream(exposure.spliterator(), false)
                .map(exposureMapper::toDto)
                .collect(Collectors.toList());
    }
}
