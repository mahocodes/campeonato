package com.campeonato.domain.service;

import campeonato.model.PartidaApiResponse;
import com.campeonato.controller.factory.InputParams;
import com.campeonato.core.exceptions.DatabaseException;
import com.campeonato.core.utils.Service;
import com.campeonato.domain.converter.PartidaConverter;
import com.campeonato.infrastructure.persistence.repository.PartidaRepository;
import com.campeonato.infrastructure.persistence.repository.TimeRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.StreamSupport.stream;

@Service
@RequiredArgsConstructor
public class PartidaService {

    private final PartidaRepository repository;

    private final PartidaConverter converter;

    private final TimeRepository timeRepository;

    public List<PartidaApiResponse> getAll() {
        var iterableEntity = repository.findAll();

        var entityList = stream(iterableEntity.spliterator(), false)
                .collect(Collectors.toList());

        return converter.toListApiResponse(entityList);
    }

    public void save(InputParams inputParams) {
        try {
            var timeMandante = timeRepository.findById(inputParams.getIdTimeMandante());
            var timeVisitante = timeRepository.findById(inputParams.getIdTimeVisitante());

            if (timeMandante.isPresent() && timeVisitante.isPresent()) {
                var entity = converter.toPartidaEntity(inputParams, timeMandante.get(), timeVisitante.get());

                repository.save(entity);
            }
        } catch (Exception exception) {
            throw new DatabaseException(exception);
        }
    }

}
