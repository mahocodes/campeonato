package com.campeonato.controller.factory;

import campeonato.model.JogadorApiRequest;
import io.micronaut.context.annotation.Factory;

@Factory
public class CampeonatoApiFactory {

    public static InputParams buildParamsJogador(JogadorApiRequest request) {
        return new InputParams.InputParamsBuilder()
                .nome(request.getNome())
                .dataNascimento(request.getDataNascimento())
                .nacionalidade(request.getNacionalidade())
                .build();
    }

}

