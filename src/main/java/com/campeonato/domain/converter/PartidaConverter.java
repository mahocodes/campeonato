package com.campeonato.domain.converter;

import campeonato.model.PartidaApiResponse;
import com.campeonato.controller.factory.InputParams;
import com.campeonato.core.utils.DateUtils;
import com.campeonato.core.utils.Service;
import com.campeonato.infrastructure.persistence.entity.PartidaEntity;
import com.campeonato.infrastructure.persistence.entity.TimeEntity;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PartidaConverter {

    private final TimeConverter timeConverter;
    private final EventoConverter eventoConverter;

    public PartidaApiResponse toApiResponse(PartidaEntity entity) {
        return PartidaApiResponse.builder()
                .id(entity.getId())
                .timeMandante(timeConverter.toApiResponse(entity.getTimeMandante()))
                .timeVisitante(timeConverter.toApiResponse(entity.getTimeVisitante()))
                .dataRealizacao(DateUtils.localDateTimeToString(entity.getDataRealizacao()))
                .eventos(eventoConverter.toListApiResponse(new ArrayList<>(entity.getEventos())))
                .build();
    }

    public List<PartidaApiResponse> toListApiResponse(List<PartidaEntity> entityList) {
        return entityList.stream().map(this::toApiResponse).collect(Collectors.toList());
    }

    public PartidaEntity toPartidaEntity(InputParams inputParams, TimeEntity timeMandante, TimeEntity timeVisitante) {
        return PartidaEntity.builder()
                .timeMandante(timeMandante)
                .timeVisitante(timeVisitante)
                .dataRealizacao(DateUtils.stringToLocalDateTime(inputParams.getDataRealizacao()))
                .build();
    }
}
