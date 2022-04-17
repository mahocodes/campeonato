package com.campeonato.core.utils;

import lombok.experimental.UtilityClass;

import java.util.Locale;
import java.util.ResourceBundle;

@UtilityClass
public class MessageUtils {

    private final Locale BRAZIL = new Locale("pt", "BR");

    public static String getMessage(String key) {
        var resourceBundle = ResourceBundle.getBundle("messages", BRAZIL);

        return resourceBundle.getString(key);
    }
}
