package com.campeonato.core.utils;

import com.campeonato.core.exceptions.BadRequestException;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@UtilityClass
public class DateUtils {

    private static final String DETALHE_DATE_ERROR = MessageUtils.getMessage("exception.response.date.error");
    private static final DateTimeFormatter FORMATTER_SIMPLE = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter FORMATTER_PT_BR = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public String localDateTimeToString(LocalDateTime localDateTime) {
        try {
            return localDateTime.format(FORMATTER_PT_BR);
        } catch (Exception exception) {
            throw new BadRequestException(DETALHE_DATE_ERROR);
        }
    }

    public String localDateToString(LocalDate localDate) {
        try {
            return localDate.format(FORMATTER_SIMPLE);
        } catch (Exception exception) {
            throw new BadRequestException(DETALHE_DATE_ERROR);
        }
    }

    public LocalDate stringToLocalDate(String localDate) {
        try {
            return LocalDate.parse(localDate, FORMATTER_SIMPLE);
        } catch (Exception exception) {
            throw new BadRequestException(DETALHE_DATE_ERROR);
        }
    }

    public LocalDateTime stringToLocalDateTime(String localDateTime) {
        try {
            return LocalDateTime.parse(localDateTime.concat(":00"), FORMATTER_PT_BR);
        } catch (Exception exception) {
            throw new BadRequestException(DETALHE_DATE_ERROR);
        }
    }
}
