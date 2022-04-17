package com.campeonato.core.exceptions;

public class BadRequestException extends DefaultException {

    private static final String CODIGO = ApiError.BAD_REQUEST_ERROR.getCodigo();
    private static final String MENSAGEM = ApiError.BAD_REQUEST_ERROR.getMensagem();

    public BadRequestException(String detalhe, Throwable throwable) {
        super(CODIGO, MENSAGEM, detalhe, throwable);
    }

    public BadRequestException(String detalhe) {
        super(CODIGO, MENSAGEM, detalhe);
    }
}
