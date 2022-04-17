package com.campeonato.core.exceptions;

public class DatabaseException extends DefaultException {

    private static final long serialVersionUID = 988045931720454106L;

    private static final String CODIGO = ApiError.DATABASE_ERROR.getCodigo();
    private static final String MENSAGEM = ApiError.DATABASE_ERROR.getMensagem();
    private static final String DETALHE = ApiError.DATABASE_ERROR.getDetalhe();

    public DatabaseException(String detalhe, Throwable throwable) {
        super(CODIGO, MENSAGEM, detalhe, throwable);
    }

    public DatabaseException(String detalhe) {
        super(CODIGO, MENSAGEM, detalhe);
    }

    public DatabaseException(Throwable throwable) {
        super(CODIGO, MENSAGEM, DETALHE, throwable);
    }
}
