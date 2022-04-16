package com.campeonato.controller.factory;

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
}
