package com.campeonato.domain.converter;

import campeonato.model.TorneioApiResponse;
import com.campeonato.controller.factory.InputParams;
import com.campeonato.core.utils.Service;
import com.campeonato.infrastructure.persistence.entity.TorneioEntity;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import static com.campeonato.core.utils.DateUtils.localDateToString;
import static com.campeonato.core.utils.DateUtils.stringToLocalDate;

@Service
@RequiredArgsConstructor
public class TorneioConverter {

    private final JogadorConverter jogadorConverter;
    private final TimeConverter timeConverter;

    public TorneioApiResponse toApiResponse(TorneioEntity entity) {
        return TorneioApiResponse.builder()
                .id(entity.getId())
                .dataInicio(localDateToString(entity.getDataInicio()))
                .dataFim(localDateToString(entity.getDataFim()))
                .timeCampeao(timeConverter.toApiResponse(entity.getCampeao()))
                .craque(jogadorConverter.toApiResponse(entity.getCraque()))
                .build();
    }

    public List<TorneioApiResponse> toListApiResponse(List<TorneioEntity> entityList) {
        return entityList.stream().map(this::toApiResponse).collect(Collectors.toList());
    }

    public TorneioEntity toTorneioEntity(InputParams inputParams) {
        return TorneioEntity.builder()
                .dataInicio(stringToLocalDate(inputParams.getDataInicio()))
                .dataFim(stringToLocalDate(inputParams.getDataFim()))
                .build();
    }

}
