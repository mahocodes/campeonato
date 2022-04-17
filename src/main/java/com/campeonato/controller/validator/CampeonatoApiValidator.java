package com.campeonato.controller.validator;

import campeonato.model.JogadorApiRequest;
import campeonato.model.PartidaApiRequest;
import campeonato.model.TimeApiRequest;
import campeonato.model.TorneioApiRequest;
import com.campeonato.core.utils.ApiValidator;

import static com.campeonato.core.constants.AppConstants.*;

public class CampeonatoApiValidator {

    public static void validate(TimeApiRequest request) {
        new ApiValidator()
                .appendCampoObrigatorioEmBranco(request.getNome(), NOME)
                .appendCampoObrigatorioEmBranco(request.getLocalidade().name(), LOCALIDADE)
                .throwBadRequestSeExistiremCamposInvalidos();
    }

    public static void validate(JogadorApiRequest request) {
        new ApiValidator()
                .appendCampoObrigatorioEmBranco(request.getNome(), NOME)
                .appendCampoObrigatorioEmBranco(request.getDataNascimento(), DATA_NASCIMENTO)
                .appendCampoObrigatorioEmBranco(request.getNacionalidade(), NACIONALIDADE)
                .throwBadRequestSeExistiremCamposInvalidos();

    }

    public static void validate(PartidaApiRequest request) {
        new ApiValidator()
                .appendCampoObrigatorioEmBranco(request.getDataRealizacao(), DATA_REALIZACAO)
                .throwBadRequestSeExistiremCamposInvalidos();
    }

    public static void validate(TorneioApiRequest request) {
        new ApiValidator()
                .appendCampoObrigatorioEmBranco(request.getDataInicio(), DATA_INICIO)
                .appendCampoObrigatorioEmBranco(request.getDataFim(), DATA_FIM)
                .throwBadRequestSeExistiremCamposInvalidos();
    }

    public static void validateDataInicio(String dataInicio) {
        new ApiValidator()
                .appendCampoObrigatorioEmBranco(dataInicio, DATA_INICIO)
                .throwBadRequestSeExistiremCamposInvalidos();
    }

    public static void validateDataFim(String dataFim) {
        new ApiValidator()
                .appendCampoObrigatorioEmBranco(dataFim, DATA_FIM)
                .throwBadRequestSeExistiremCamposInvalidos();
    }
}
