package com.campeonato.domain.service;

import campeonato.model.TimeApiResponse;
import com.campeonato.controller.factory.InputParams;
import com.campeonato.core.utils.Service;
import com.campeonato.infrastructure.persistence.repository.TimeRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TimeService {

    private final TimeRepository repository;

    public void delete(Long id) {

    }

    public List<TimeApiResponse> getAll() {
        return null;
    }

    public TimeApiResponse getById(Long id) {
        return null;
    }

    public void post(InputParams inputParams) {

    }

    public void put(Long id, InputParams inputParams) {

    }
}
