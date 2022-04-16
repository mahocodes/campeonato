package com.campeonato;

import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "campeonato",
                version = "0.0"
        )
)
public class App {

    public static void main(String[] args) {
        Micronaut.run(App.class, args);
    }
}
