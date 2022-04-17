package com.campeonato.domain.service;

import campeonato.model.JogadorApiResponse;
import com.campeonato.controller.factory.InputParams;
import com.campeonato.core.utils.Service;
import com.campeonato.infrastructure.persistence.repository.JogadorRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JogadorService {

    private final JogadorRepository repository;

    public void delete(Long id) {

    }

    public List<JogadorApiResponse> getAll() {
        return null;
    }

    public JogadorApiResponse getById(Long id) {
        return null;
    }

    public void post(InputParams inputParams) {

    }

    public void put(Long id, InputParams inputParams) {

    }
}
