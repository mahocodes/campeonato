package com.campeonato.controller;

import campeonato.model.JogadorApiRequest;
import campeonato.model.JogadorApiResponse;
import com.campeonato.domain.service.JogadorService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

import javax.validation.Valid;
import java.util.List;

import static com.campeonato.controller.factory.CampeonatoApiFactory.buildParamsJogador;
import static com.campeonato.controller.validator.CampeonatoApiValidator.validate;
import static io.micronaut.core.util.CollectionUtils.isEmpty;
import static io.micronaut.http.HttpResponse.*;
import static java.util.Objects.isNull;

@Controller
@RequiredArgsConstructor
public class JogadorController {

    private final JogadorService service;

    @Operation(summary = "Deleta um jogador", operationId = "deleteJogadorById", description = "Exclui um jogador da base de dados", tags = {"Jogadores"})
    @ApiResponse(responseCode = "200", description = "Deletado com sucesso")
    @ApiResponse(responseCode = "400", description = "Id inválido")
    @ApiResponse(responseCode = "404", description = "Id não encontrado")
    @Delete(value = "/jogadores/{id}", produces = {"application/json"})
    public HttpResponse<Void> deleteJogadorById(@PathVariable("id") Long id) {
        service.delete(id);

        return ok();
    }

    @Operation(summary = "Buscar todos os jogadores", operationId = "getAllJogadores", description = "Retorna uma lista de jogadores", tags = {"Jogadores"})
    @ApiResponse(responseCode = "200", description = "Operação bem sucedida")
    @ApiResponse(responseCode = "400", description = "Id inválido")
    @ApiResponse(responseCode = "404", description = "Id não encontrado")
    @Get(value = "/jogadores", produces = {"application/json"})
    public HttpResponse<List> getAllJogadores() {
        var response = service.getAll();

        return isEmpty(response) ? notFound() : ok(response);
    }

    @Operation(summary = "Busca um jogador for id", operationId = "getJogadorById", description = "Retorna as informações de um jogador", tags = {"Jogadores"})
    @ApiResponse(responseCode = "200", description = "Operação bem sucedida")
    @ApiResponse(responseCode = "400", description = "Id inválido")
    @ApiResponse(responseCode = "404", description = "Id não encontrado")
    @Get(value = "/jogadores/{id}", produces = {"application/json"})
    public HttpResponse<JogadorApiResponse> getJogadorById(@PathVariable("id") Long id) {
        var response = service.getById(id);

        return isNull(response) ? notFound() : ok(response);
    }

    @Operation(summary = "Cadastrar um jogador", operationId = "postJogador", description = "Registra um novo jogador", tags = {"Jogadores"})
    @ApiResponse(responseCode = "201", description = "Created")
    @ApiResponse(responseCode = "422", description = "Error")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @Post(value = "/jogadores", produces = {"application/json"}, consumes = {"application/json"})
    public HttpResponse<Void> postJogador(@Valid @Body JogadorApiRequest request) {
        validate(request);

        var inputParams = buildParamsJogador(request);

        service.save(inputParams);

        return noContent();
    }

    @Operation(summary = "Alterar um jogador", operationId = "putJogador", description = "Modifica as informações de um jogador", tags = {"Jogadores"})
    @ApiResponse(responseCode = "204", description = "Operação bem sucedida")
    @ApiResponse(responseCode = "400", description = "Id inválido")
    @ApiResponse(responseCode = "404", description = "Id não encontrado")
    @Put(value = "/jogadores/{id}", produces = {"application/json"}, consumes = {"application/json"})
    public HttpResponse<Void> putJogador(@Valid @Body JogadorApiRequest request, @PathVariable("id") Long id) {
        validate(request);

        var inputParams = buildParamsJogador(request);

        service.updateById(id, inputParams);

        return noContent();
    }
}
