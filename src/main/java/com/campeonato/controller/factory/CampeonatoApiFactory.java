package com.campeonato.controller.factory;

import campeonato.model.JogadorApiRequest;
import campeonato.model.TimeApiRequest;
import io.micronaut.context.annotation.Factory;

@Factory
public class CampeonatoApiFactory {

    public static InputParams buildParamsJogador(JogadorApiRequest request) {
        return new InputParams.InputParamsBuilder()
                .nome(request.getNome())
                .dataNascimento(request.getDataNascimento())
                .nacionalidade(request.getNacionalidade())
                .idTime(request.getIdTime())
                .build();
    }

    public static InputParams buildParamsTime(TimeApiRequest request) {
        return new InputParams.InputParamsBuilder()
                .nome(request.getNome())
                .localidade(request.getLocalidade())
                .build();
    }
}

