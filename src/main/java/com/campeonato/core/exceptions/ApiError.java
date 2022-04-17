package com.campeonato.core.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ApiError {

    // CB - Campeonato brasileiro, XXX - IDENTIFICACAO DO ERRO, 000 - No SERIAL

    BAD_REQUEST_ERROR("[CBBRE001]", "exception.request.badrequest.mensagem", "exception.request.badrequest.detalhe"),
    DATABASE_ERROR("[CBDBE001]", "exception.response.database.error.mensagem", "exception.response.database.error.detalhe");

    private String codigo;
    private String mensagem;
    private String detalhe;
}
