package com.campeonato.controller;

import campeonato.model.PartidaApiRequest;
import com.campeonato.controller.factory.CampeonatoApiFactory;
import com.campeonato.controller.factory.InputParams;
import com.campeonato.domain.service.EventoService;
import com.campeonato.domain.service.PartidaService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

import static com.campeonato.controller.factory.CampeonatoApiFactory.*;
import static com.campeonato.controller.validator.CampeonatoApiValidator.*;
import static io.micronaut.core.util.CollectionUtils.isEmpty;
import static io.micronaut.http.HttpResponse.*;

@Controller
@RequiredArgsConstructor
public class PartidaController {

    private final PartidaService service;
    private final EventoService eventoService;

    @Operation(summary = "Buscar todas as partidas", operationId = "getAllPartidas", description = "Retorna uma lista de partidas", tags = {"Partidas"})
    @ApiResponse(responseCode = "200", description = "Operação bem sucedida")
    @ApiResponse(responseCode = "400", description = "Id inválido")
    @ApiResponse(responseCode = "404", description = "Id não encontrado")
    @Get(value = "/partidas", produces = {"application/json"})
    public HttpResponse<List> getAllPartidas() {
        var response = service.getAll();

        return isEmpty(response) ? notFound() : ok(response);
    }

    private HttpResponse<Void> postEvento(InputParams inputParams) {
        eventoService.save(inputParams);

        return noContent();
    }

    @Operation(summary = "Cadastrar um acrescimo", operationId = "postAcrescimo", description = "Registra um novo acrescimo", tags = {"Partidas"})
    @ApiResponse(responseCode = "201", description = "Created")
    @ApiResponse(responseCode = "422", description = "Error")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @Post(value = "/torneios/{idTorneio}/partidas/{idPartida}/eventos/acrescimo", produces = {"application/json"})
    public HttpResponse<Void> postAcrescimo(@PathVariable("idTorneio") Long idTorneio, @PathVariable("idPartida") Long idPartida, @NotNull @Valid @Header(value = "tempo") Integer tempo, @NotNull @Valid @Header(value = "minutos") Integer minutos) {
        var inputParams = CampeonatoApiFactory.buildParamsEventoAcrescimo(idTorneio, idPartida, tempo, minutos);

        return postEvento(inputParams);
    }

    @Operation(summary = "Cadastrar uma advertencia", operationId = "postAdvertencia", description = "Registra uma nova advertencia", tags = {"Partidas"})
    @ApiResponse(responseCode = "201", description = "Created")
    @ApiResponse(responseCode = "422", description = "Error")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @Post(value = "/torneios/{idTorneio}/partidas/{idPartida}/eventos/advertencia", produces = {"application/json"})
    public HttpResponse<Void> postAdvertencia(@PathVariable("idTorneio") Long idTorneio, @PathVariable("idPartida") Long idPartida, @NotNull @Valid @Header(value = "tipo") String tipo, @NotNull @Valid @Header(value = "idJogador") Long idJogador) {
        var inputParams = CampeonatoApiFactory.buildParamsEventoAdvertencia(idTorneio, idPartida, tipo, idJogador);

        return postEvento(inputParams);
    }


    @Operation(summary = "Cadastrar o fim da partida", operationId = "postFim", description = "Registra o fim da partida", tags = {"Partidas"})
    @ApiResponse(responseCode = "201", description = "Created")
    @ApiResponse(responseCode = "422", description = "Error")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @Post(value = "/torneios/{idTorneio}/partidas/{idPartida}/eventos/fim", produces = {"application/json"})
    public HttpResponse<Void> postFim(@PathVariable("idTorneio") Long idTorneio, @PathVariable("idPartida") Long idPartida, @NotNull @Valid @Header(value = "dataFim") String dataFim) {
        validateDataFim(dataFim);

        var inputParams = CampeonatoApiFactory.buildParamsEventoInicioFim(idTorneio, idPartida, dataFim);

        return postEvento(inputParams);
    }

    @Operation(summary = "Cadastrar um gol", operationId = "postGol", description = "Registra um novo gol", tags = {"Partidas"})
    @ApiResponse(responseCode = "201", description = "Created")
    @ApiResponse(responseCode = "422", description = "Error")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @Post(value = "/torneios/{idTorneio}/partidas/{idPartida}/eventos/gol", produces = {"application/json"})
    public HttpResponse<Void> postGol(@PathVariable("idTorneio") Long idTorneio, @PathVariable("idPartida") Long idPartida, @NotNull @Valid @Header(value = "minuto") Integer minuto, @NotNull @Valid @Header(value = "idJogador") Long idJogador) {
        var inputParams = CampeonatoApiFactory.buildParamsEventoGol(idTorneio, idPartida, minuto, idJogador);

        return postEvento(inputParams);
    }

    @Operation(summary = "Cadastrar um início de partida", operationId = "postInicio", description = "Registra o início de uma nova partida", tags = {"Partidas"})
    @ApiResponse(responseCode = "201", description = "Created")
    @ApiResponse(responseCode = "422", description = "Error")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @Post(value = "/torneios/{idTorneio}/partidas/{idPartida}/eventos/inicio", produces = {"application/json"})
    public HttpResponse<Void> postInicio(@PathVariable("idTorneio") Long idTorneio, @PathVariable("idPartida") Long idPartida, @NotNull @Valid @Header(value = "dataInicio") String dataInicio) {
        validateDataInicio(dataInicio);

        var inputParams = buildParamsEventoInicioFim(idTorneio, idPartida, dataInicio);

        return postEvento(inputParams);
    }

    @Operation(summary = "Cadastrar uma partida", operationId = "postPartida", description = "Registra uma nova partida", tags = {"Partidas"})
    @ApiResponse(responseCode = "201", description = "Created")
    @ApiResponse(responseCode = "422", description = "Error")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @Post(value = "/partidas", produces = {"application/json"}, consumes = {"application/json"})
    public HttpResponse<Void> postPartida(@Valid @Body PartidaApiRequest request) {
        validate(request);

        var inputParams = buildParamsEvento(request);

        return postEvento(inputParams);
    }

    @Operation(summary = "Cadastrar uma substituicao", operationId = "postSubstituicao", description = "Registra uma nova substituicao", tags = {"Partidas"})
    @ApiResponse(responseCode = "201", description = "Created")
    @ApiResponse(responseCode = "422", description = "Error")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @Post(value = "/torneios/{idTorneio}/partidas/{idPartida}/eventos/substituicao", produces = {"application/json"})
    public HttpResponse<Void> postSubstituicao(@PathVariable("idTorneio") Long idTorneio, @PathVariable("idPartida") Long idPartida, @NotNull @Valid @Header(value = "idJogadorSubstituido") Long idJogadorSubstituido, @NotNull @Valid @Header(value = "idJogadorSubstituto") Long idJogadorSubstituto, @NotNull @Valid @Header(value = "minuto") Integer minuto) {
        var inputParams = buildParamsEventoSubtituicao(idTorneio, idPartida, idJogadorSubstituido, idJogadorSubstituto, minuto);

        return postEvento(inputParams);
    }
}
