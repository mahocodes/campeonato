package com.campeonato.domain.converter;

import campeonato.model.Evento;
import campeonato.model.TipoAdvertencia;
import campeonato.model.TipoEvento;
import com.campeonato.controller.factory.InputParams;
import com.campeonato.core.utils.DateUtils;
import com.campeonato.core.utils.Service;
import com.campeonato.infrastructure.persistence.entity.EventoEntity;
import com.campeonato.infrastructure.persistence.entity.JogadorEntity;
import com.campeonato.infrastructure.persistence.entity.PartidaEntity;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventoConverter {

    private final JogadorConverter jogadorConverter;
    private final PartidaConverter partidaConverter;

    public Evento toApiResponse(EventoEntity entity) {
        return Evento.builder()
                .id(entity.getId())
                .data(DateUtils.localDateTimeToString(entity.getData()))
                .tempo(entity.getTempo())
                .minutos(entity.getMinutos())
                .tipo(TipoEvento.valueOf(entity.getTipoEvento().name()))
                .gravidade(TipoAdvertencia.valueOf(entity.getTipoAdvertencia().name()))
                .jogador(jogadorConverter.toApiResponse(entity.getJogador()))
                .jogadorSubstituto(jogadorConverter.toApiResponse(entity.getJogadorSubstituto()))
                .partida(partidaConverter.toApiResponse(entity.getPartida()))
                .build();
    }

    public List<Evento> toListApiResponse(List<EventoEntity> entityList) {
        return entityList.stream().map(this::toApiResponse).collect(Collectors.toList());
    }

    public EventoEntity toEventoInicioEntity(InputParams inputParams, PartidaEntity partidaEntity) {
        return EventoEntity.builder()
                .tipoEvento(com.campeonato.infrastructure.persistence.entity.TipoEvento.INICIO)
                .data(DateUtils.stringToLocalDateTime(inputParams.getDataInicio()))
                .partida(partidaEntity)
                .build();
    }

    public EventoEntity toEventoGolEntity(InputParams inputParams, PartidaEntity partidaEntity, JogadorEntity jogadorEntity) {
        return EventoEntity.builder()
                .tipoEvento(com.campeonato.infrastructure.persistence.entity.TipoEvento.GOL)
                .minuto(inputParams.getMinuto())
                .partida(partidaEntity)
                .jogador(jogadorEntity)
                .build();
    }

    public EventoEntity toEventoAcrescimoEntity(InputParams inputParams, PartidaEntity partidaEntity) {
        return EventoEntity.builder()
                .tipoEvento(com.campeonato.infrastructure.persistence.entity.TipoEvento.ACRESCIMO)
                .tempo(inputParams.getTempo())
                .minutos(inputParams.getMinutos())
                .partida(partidaEntity)
                .build();
    }

    public EventoEntity toEventoSubstituicaoEntity(InputParams inputParams, PartidaEntity partidaEntity, JogadorEntity jogadorEntity, JogadorEntity jogadorSubstituto) {
        return EventoEntity.builder()
                .tipoEvento(com.campeonato.infrastructure.persistence.entity.TipoEvento.SUBSTITUICAO)
                .minuto(inputParams.getMinuto())
                .partida(partidaEntity)
                .jogador(jogadorEntity)
                .jogadorSubstituto(jogadorSubstituto)
                .build();
    }


    public EventoEntity toEventoAdvertenciaEntity(InputParams inputParams, PartidaEntity partidaEntity, JogadorEntity jogadorEntity) {
        return EventoEntity.builder()
                .tipoEvento(com.campeonato.infrastructure.persistence.entity.TipoEvento.ADVERTENCIA)
                .tipoAdvertencia(com.campeonato.infrastructure.persistence.entity.TipoAdvertencia.valueOf(inputParams.getTipoAdvertencia().name()))
                .partida(partidaEntity)
                .jogador(jogadorEntity)
                .build();
    }

    public EventoEntity toEventoFimEntity(InputParams inputParams, PartidaEntity partidaEntity) {
        return EventoEntity.builder()
                .tipoEvento(com.campeonato.infrastructure.persistence.entity.TipoEvento.FIM)
                .data(DateUtils.stringToLocalDateTime(inputParams.getDataFim()))
                .partida(partidaEntity)
                .build();
    }

}
