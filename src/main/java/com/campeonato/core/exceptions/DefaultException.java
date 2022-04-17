package com.campeonato.core.exceptions;

import lombok.Getter;

@Getter
public class DefaultException extends RuntimeException {

    private static final long serialVersionUID = -4269352008446628312L;

    public DefaultException(String codigo, String mensagem, String detalhe, Throwable throwable) {
        super(codigo.concat(" ").concat(mensagem).concat(" ").concat(detalhe), throwable);
    }
}
