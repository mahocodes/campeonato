package com.campeonato.domain.service;

import campeonato.model.TimeApiResponse;
import com.campeonato.controller.factory.InputParams;
import com.campeonato.core.utils.Service;
import com.campeonato.infrastructure.persistence.repository.EventoRepository;
import com.campeonato.infrastructure.persistence.repository.PartidaRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PartidaService {

    private final PartidaRepository repository;

    private final EventoRepository eventoRepository;

    public List<TimeApiResponse> getAll() {
        return null;
    }

    public void post(InputParams inputParams) {

    }

    public void postEvento(InputParams inputParams) {

    }

}
