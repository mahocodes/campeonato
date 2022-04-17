package com.campeonato.domain.service;

import campeonato.model.TimeApiResponse;
import com.campeonato.controller.factory.InputParams;
import com.campeonato.core.exceptions.DatabaseException;
import com.campeonato.core.utils.Service;
import com.campeonato.domain.converter.TimeConverter;
import com.campeonato.infrastructure.persistence.repository.TimeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.StreamSupport.stream;

@Slf4j
@Service
@RequiredArgsConstructor
public class TimeService {

    private static final String DETALHE = "Id de time não encontrado";

    private final TimeRepository repository;
    private final TimeConverter converter;

    public void delete(Long id) {
        var entity = repository.findById(id);

        entity.ifPresent(repository::delete);
    }

    public List<TimeApiResponse> getAll() {
        var iterableEntity = repository.findAll();

        var entityList = stream(iterableEntity.spliterator(), false)
                .collect(Collectors.toList());

        return converter.toListApiResponse(entityList);
    }

    public TimeApiResponse getById(Long id) {
        var entity = repository.findById(id);

        return entity.map(converter::toApiResponse).orElse(null);
    }

    public void save(InputParams inputParams) {
        var entity = converter.toTimeEntity(inputParams);

        repository.save(entity);
    }

    public void updateById(Long id, InputParams inputParams) {
        try {
            var entity = converter.toTimeEntity(inputParams);

            entity.setId(id);

            repository.update(entity);
        } catch (Exception exception) {
            log.error(DETALHE);

            throw new DatabaseException(exception);
        }
    }
}
