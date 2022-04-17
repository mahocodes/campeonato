package com.campeonato.domain.converter;

import campeonato.model.TimeApiResponse;
import com.campeonato.controller.factory.InputParams;
import com.campeonato.core.utils.Service;
import com.campeonato.infrastructure.persistence.entity.TimeEntity;

import java.util.List;
import java.util.stream.Collectors;

import static campeonato.model.Localidade.valueOf;

@Service
public class TimeConverter {

    public TimeApiResponse toApiResponse(TimeEntity entity) {
        return TimeApiResponse.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .localidade(valueOf(entity.getLocalidade().name()))
                .build();
    }

    public List<TimeApiResponse> toListApiResponse(List<TimeEntity> entityList) {
        return entityList.stream().map(this::toApiResponse).collect(Collectors.toList());
    }

    public TimeEntity toTimeEntity(InputParams inputParams) {
        return TimeEntity.builder()
                .nome(inputParams.getNome())
                .localidade(com.campeonato.infrastructure.persistence.entity.Localidade.valueOf(inputParams.getLocalidade().name()))
                .build();
    }

}
