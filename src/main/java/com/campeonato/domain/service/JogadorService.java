package com.campeonato.domain.service;

import campeonato.model.JogadorApiResponse;
import com.campeonato.controller.factory.InputParams;
import com.campeonato.core.exceptions.DatabaseException;
import com.campeonato.core.utils.Service;
import com.campeonato.domain.converter.JogadorConverter;
import com.campeonato.infrastructure.persistence.repository.JogadorRepository;
import com.campeonato.infrastructure.persistence.repository.TimeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.StreamSupport.stream;

@Slf4j
@Service
@RequiredArgsConstructor
public class JogadorService {

    private static final String DETALHE = "Id de jogador não encontrado";
    private static final String DETALHE_TIME = "Id de time não encontrado";

    private final JogadorRepository repository;
    private final TimeRepository timeRepository;
    private final JogadorConverter converter;

    public void delete(Long id) {
        var jogadorEntity = repository.findById(id);

        jogadorEntity.ifPresent(repository::delete);
    }

    public List<JogadorApiResponse> getAll() {
        var iterableEntity = repository.findAll();

        var jogadorEntityList = stream(iterableEntity.spliterator(), false)
                .collect(Collectors.toList());

        return converter.toListApiResponse(jogadorEntityList);
    }

    public JogadorApiResponse getById(Long id) {
        var entity = repository.findById(id);

        return entity.map(converter::toApiResponse).orElse(null);
    }

    public void save(InputParams inputParams) {
        try {
            var optionalEntity = timeRepository.findById(inputParams.getIdTime());

            var entity = optionalEntity.map(e -> converter.toJogadorEntity(inputParams, e)).orElseThrow();

            repository.save(entity);
        } catch (Exception exception) {
            log.error(DETALHE_TIME);

            throw new DatabaseException(exception);
        }
    }

    public void updateById(Long id, InputParams inputParams) {
        try {
            var entity = repository.findById(id);

            entity.map(repository::update).orElseThrow();
        } catch (Exception exception) {
            log.error(DETALHE);

            throw new DatabaseException(exception);
        }
    }
}
