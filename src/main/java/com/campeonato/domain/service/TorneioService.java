package com.campeonato.domain.service;

import campeonato.model.TorneioApiResponse;
import com.campeonato.controller.factory.InputParams;
import com.campeonato.core.exceptions.DatabaseException;
import com.campeonato.core.utils.Service;
import com.campeonato.domain.converter.TorneioConverter;
import com.campeonato.infrastructure.persistence.repository.TorneioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.StreamSupport.stream;

@Slf4j
@Service
@RequiredArgsConstructor
public class TorneioService {

    private static final String DETALHE = "Id de torneio não encontrado";

    private final TorneioRepository repository;
    private final TorneioConverter converter;

    public List<TorneioApiResponse> getAll() {
        var iterableEntity = repository.findAll();

        var entityList = stream(iterableEntity.spliterator(), false)
                .collect(Collectors.toList());

        return converter.toListApiResponse(entityList);
    }

    public TorneioApiResponse getById(Long id) {
        var entity = repository.findById(id);

        return entity.map(converter::toApiResponse).orElse(null);
    }


    public void save(InputParams inputParams) {
        try {
            var entity = converter.toTorneioEntity(inputParams);

            repository.save(entity);
        } catch (Exception exception) {
            throw new DatabaseException(exception);
        }
    }

    public void updateById(Long id, InputParams inputParams) {
        try {
            var entity = converter.toTorneioEntity(inputParams);

            entity.setId(id);

            repository.update(entity);
        } catch (Exception exception) {
            log.error(DETALHE);

            throw new DatabaseException(exception);
        }
    }
}
