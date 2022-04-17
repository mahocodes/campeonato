package com.campeonato.domain.converter;

import campeonato.model.JogadorApiResponse;
import com.campeonato.controller.factory.InputParams;
import com.campeonato.core.utils.DateUtils;
import com.campeonato.core.utils.Service;
import com.campeonato.infrastructure.persistence.entity.JogadorEntity;
import com.campeonato.infrastructure.persistence.entity.TimeEntity;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.valueOf;

@Service
public class JogadorConverter {

    public JogadorApiResponse toApiResponse(JogadorEntity entity) {
        return JogadorApiResponse.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .dataNascimento(valueOf(entity.getDataNascimento()))
                .nacionalidade(entity.getNacionalidade())
                .build();
    }

    public List<JogadorApiResponse> toListApiResponse(List<JogadorEntity> entityList) {
        return entityList.stream().map(this::toApiResponse).collect(Collectors.toList());
    }

    public JogadorEntity toJogadorEntity(InputParams inputParams, TimeEntity timeEntity) {
        return JogadorEntity.builder()
                .nome(inputParams.getNome())
                .dataNascimento(DateUtils.stringToLocalDate(inputParams.getDataNascimento()))
                .nacionalidade(inputParams.getNacionalidade())
                .time(timeEntity)
                .build();
    }

}
