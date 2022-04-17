package com.campeonato.domain.service;

import com.campeonato.controller.factory.InputParams;
import com.campeonato.core.exceptions.DatabaseException;
import com.campeonato.core.utils.Service;
import com.campeonato.domain.converter.EventoConverter;
import com.campeonato.infrastructure.persistence.entity.EventoEntity;
import com.campeonato.infrastructure.persistence.entity.JogadorEntity;
import com.campeonato.infrastructure.persistence.entity.PartidaEntity;
import com.campeonato.infrastructure.persistence.repository.EventoRepository;
import com.campeonato.infrastructure.persistence.repository.JogadorRepository;
import com.campeonato.infrastructure.persistence.repository.PartidaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

import static campeonato.model.TipoEvento.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventoService {

    private final EventoConverter converter;

    private final EventoRepository repository;

    private final PartidaRepository partidaRepository;

    private final JogadorRepository jogadorRepository;

    public void saveEvento(InputParams inputParams) {
        try {
            var eventoEntity = new EventoEntity();

            var partidaEntity = getOptionalPartida(inputParams).orElseThrow();

            if (INICIO.equals(inputParams.getTipoEvento())) {
                eventoEntity = converter.toEventoInicioEntity(inputParams, partidaEntity);
            } else if (GOL.equals(inputParams.getTipoEvento())) {
                var jogadorEntity = getOptionalJogador(inputParams).orElseThrow();

                eventoEntity = converter.toEventoGolEntity(inputParams, partidaEntity, jogadorEntity);
            } else if (ACRESCIMO.equals(inputParams.getTipoEvento())) {
                eventoEntity = converter.toEventoAcrescimoEntity(inputParams, partidaEntity);
            } else if (SUBSTITUICAO.equals(inputParams.getTipoEvento())) {
                var jogadorEntity = getOptionalJogador(inputParams).orElseThrow();
                var substitutoEntity = getOptionalJogadorSubstituto(inputParams).orElseThrow();

                eventoEntity = converter.toEventoSubstituicaoEntity(inputParams, partidaEntity, jogadorEntity, substitutoEntity);
            } else if (ADVERTENCIA.equals(inputParams.getTipoEvento())) {
                var jogadorEntity = getOptionalJogador(inputParams).orElseThrow();

                eventoEntity = converter.toEventoAdvertenciaEntity(inputParams, partidaEntity, jogadorEntity);
            } else {
                eventoEntity = converter.toEventoFimEntity(inputParams, partidaEntity);
            }


            repository.save(eventoEntity);

        } catch (Exception exception) {
            throw new DatabaseException(exception);
        }
    }

    private Optional<PartidaEntity> getOptionalPartida(InputParams inputParams) {
        return partidaRepository.findById(inputParams.getIdPartida());
    }

    private Optional<JogadorEntity> getOptionalJogador(InputParams inputParams) {
        return jogadorRepository.findById(inputParams.getIdJogador());
    }

    private Optional<JogadorEntity> getOptionalJogadorSubstituto(InputParams inputParams) {
        return jogadorRepository.findById(inputParams.getIdJogadorSubstituto());
    }
}
