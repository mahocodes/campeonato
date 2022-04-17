package com.campeonato.core.utils;

import com.campeonato.core.exceptions.BadRequestException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import static java.lang.String.format;
import static org.apache.commons.lang3.StringUtils.*;

@Slf4j
@Getter
public class ApiValidator {

    private static final String DETALHE = MessageUtils.getMessage("exception.request.badrequest.detalhe");

    private final StringBuilder camposInvalidos = new StringBuilder();

    public ApiValidator appendCampoObrigatorioEmBranco(String campo, String nome) {
        if (isBlank(campo)) {
            this.camposInvalidos.append(format("%s, ", nome));
        }
        return this;
    }

    public ApiValidator throwBadRequestSeExistiremCamposInvalidos() {
        var campos = this.camposInvalidos.toString();

        if (isNotBlank(campos)) {
            campos = removerVirgulasDesnecessarias(campos);
            var detalhe = format(DETALHE, campos);
            log.error(detalhe);
            throw new BadRequestException(stripAccents(detalhe));
        }

        return this;
    }

    private String removerVirgulasDesnecessarias(String campos) {
        return campos.substring(0, campos.length() - 2);
    }
}
