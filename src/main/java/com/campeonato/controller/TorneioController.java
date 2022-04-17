package com.campeonato.controller;

import campeonato.model.TorneioApiRequest;
import campeonato.model.TorneioApiResponse;
import com.campeonato.domain.service.TorneioService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

import javax.validation.Valid;
import java.util.List;

import static com.campeonato.controller.factory.CampeonatoApiFactory.buildParamsTorneio;
import static com.campeonato.controller.validator.CampeonatoApiValidator.validate;
import static io.micronaut.core.util.CollectionUtils.isEmpty;
import static io.micronaut.http.HttpResponse.*;
import static java.util.Objects.isNull;

@Controller
@RequiredArgsConstructor
public class TorneioController {

    private final TorneioService service;

    @Operation(summary = "Buscar todos os torneios", operationId = "getAllTorneios", description = "Retorna uma lista de torneios", tags = {"Torneios"})
    @ApiResponse(responseCode = "200", description = "Operação bem sucedida")
    @ApiResponse(responseCode = "400", description = "Id inválido")
    @ApiResponse(responseCode = "404", description = "Id não encontrado")
    @Get(value = "/torneios", produces = {"application/json"})
    public HttpResponse<List> getAllTorneios() {
        var response = service.getAll();

        return isEmpty(response) ? notFound() : ok(response);
    }

    @Operation(summary = "Busca um torneio por id", operationId = "getTorneioById", description = "Retorna as informações de um torneio", tags = {"Torneios"})
    @ApiResponse(responseCode = "200", description = "Operação bem sucedida")
    @ApiResponse(responseCode = "400", description = "Id inválido")
    @ApiResponse(responseCode = "404", description = "Id não encontrado")
    @Get(value = "/torneios/{id}", produces = {"application/json"})
    public HttpResponse<TorneioApiResponse> getTorneioById(@PathVariable("id") Long id) {
        var response = service.getById(id);

        return isNull(response) ? notFound() : ok(response);
    }

    @Operation(summary = "Cadastrar um torneio", operationId = "postTorneio", description = "Registra um novo torneio", tags = {"Torneios"})
    @ApiResponse(responseCode = "201", description = "Created")
    @ApiResponse(responseCode = "422", description = "Error")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @Post(value = "/torneios", produces = {"application/json"}, consumes = {"application/json"})
    public HttpResponse<Void> postTorneio(@Valid @Body TorneioApiRequest request) {
        validate(request);

        var inputParams = buildParamsTorneio(request);

        service.save(inputParams);

        return noContent();
    }

    @Operation(summary = "Alterar um torneio", operationId = "putTorneioById", description = "Modifica as informações de um torneio", tags = {"Torneios"})
    @ApiResponse(responseCode = "204", description = "Operação bem sucedida")
    @ApiResponse(responseCode = "400", description = "Id inválido")
    @ApiResponse(responseCode = "404", description = "Id não encontrado")
    @Put(value = "/torneios/{id}", produces = {"application/json"}, consumes = {"application/json"})
    public HttpResponse<Void> putTorneioById(@Valid @Body TorneioApiRequest request, @PathVariable("id") Long id) {
        validate(request);

        var inputParams = buildParamsTorneio(request);

        service.updateById(id, inputParams);

        return noContent();
    }
}
