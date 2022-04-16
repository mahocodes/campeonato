package com.campeonato.controller.factory;

import campeonato.model.JogadorApiRequest;
import campeonato.model.PartidaApiRequest;
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

    public static InputParams buildParamsEventoAcrescimo(Long idTorneio, Long idPartida, Integer tempo, Integer minutos) {
        return new InputParams.InputParamsBuilder()
                .idTorneio(idTorneio)
                .idPartida(idPartida)
                .tempo(tempo)
                .minutos(minutos)
                .build();
    }

    public static InputParams buildParamsEventoAdvertencia(Long idTorneio, Long idPartida, String tipoAdvertencia, Long idJogador) {
        return new InputParams.InputParamsBuilder()
                .idTorneio(idTorneio)
                .idPartida(idPartida)
                .build();
    }


    public static InputParams buildParamsEventoInicioFim(Long idTorneio, Long idPartida, String data) {
        return new InputParams.InputParamsBuilder()
                .idTorneio(idTorneio)
                .idPartida(idPartida)
                .build();
    }

    public static InputParams buildParamsEventoGol(Long idTorneio, Long idPartida, Integer minuto, Long idJogador) {
        return new InputParams.InputParamsBuilder()
                .idTorneio(idTorneio)
                .idPartida(idPartida)
                .build();
    }

    public static InputParams buildParamsEvento(PartidaApiRequest request) {
        return new InputParams.InputParamsBuilder()
                .idTorneio(request.getIdTorneio())
                .data(request.getDataRealizacao())
                .idTimeMandante(request.getIdTimeMandante())
                .idTimeVisitante(request.getIdTimeVisitante())
                .build();
    }

    public static InputParams buildParamsEventoSubtituicao(Long idTorneio, Long idPartida, Long idJogadorSubstituido, Long idJogadorSubstituto, Integer minuto) {
        return new InputParams.InputParamsBuilder()
                .idTorneio(idTorneio)
                .idPartida(idPartida)
                .idJogador(idJogadorSubstituido)
                .idJogadorSubstituto(idJogadorSubstituto)
                .minuto(minuto)
                .build();
    }
}

