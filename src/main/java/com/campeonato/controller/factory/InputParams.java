package com.campeonato.controller.factory;

import campeonato.model.Localidade;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
public class InputParams {

    private final String nome;

    private final String dataNascimento;

    private final String nacionalidade;

    private final Long idTime;

    private final Localidade localidade;
}
