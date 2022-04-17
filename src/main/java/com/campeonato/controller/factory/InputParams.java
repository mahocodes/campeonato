package com.campeonato.controller.factory;

import campeonato.model.Localidade;
import campeonato.model.TipoAdvertencia;
import campeonato.model.TipoEvento;
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

    private final Long idTorneio;

    private final Long idPartida;

    private final Integer tempo;

    private final Integer minutos;

    private final Integer minuto;

    private final TipoEvento tipoEvento;

    private final TipoAdvertencia tipoAdvertencia;

    private final Long idJogador;

    private final Long idJogadorSubstituto;

    private final String dataInicio;

    private final String dataFim;

    private final String dataRealizacao;

    private final Long idTimeCampeao;

    private final Long idCraque;

    private final Long idTimeMandante;

    private final Long idTimeVisitante;

}
