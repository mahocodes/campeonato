package com.campeonato.domain.service;

import campeonato.model.TorneioApiResponse;
import com.campeonato.controller.factory.InputParams;
import com.campeonato.core.utils.Service;
import com.campeonato.infrastructure.persistence.repository.TorneioRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TorneioService {

    private final TorneioRepository repository;

    public List<TorneioApiResponse> getAll() {
        return null;
    }

    public TorneioApiResponse getById(Long id) {
        return null;
    }


    public void post(InputParams inputParams) {

    }

    public void put(Long id, InputParams inputParams) {

    }
}
