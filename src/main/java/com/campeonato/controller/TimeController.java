package com.campeonato.controller;

import campeonato.model.TimeApiRequest;
import campeonato.model.TimeApiResponse;
import com.campeonato.domain.service.TimeService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import javax.validation.Valid;
import java.util.List;

import static com.campeonato.controller.factory.CampeonatoApiFactory.buildParamsTime;
import static com.campeonato.controller.validator.CampeonatoApiValidator.validate;
import static io.micronaut.core.util.CollectionUtils.isEmpty;
import static io.micronaut.http.HttpResponse.*;
import static java.util.Objects.isNull;

@Controller
public class TimeController {

    private final TimeService service;

    public TimeController(TimeService service) {
        this.service = service;
    }

    @Operation(summary = "Deleta um jogador", operationId = "deleteTimeById", description = "Exclui um jogador da base de dados", tags = {"Times"})
    @ApiResponse(responseCode = "200", description = "Deletado com sucesso")
    @ApiResponse(responseCode = "400", description = "Id inválido")
    @ApiResponse(responseCode = "404", description = "Id não encontrado")
    @Delete(value = "/times/{id}", produces = {"application/json"})
    public HttpResponse<Void> deleteTimeById(@PathVariable("id") Long id) {
        service.delete(id);

        return ok();
    }

    @Operation(summary = "Buscar todos os times", operationId = "getAllTimes", description = "Retorna uma lista de times", tags = {"Times"})
    @ApiResponse(responseCode = "200", description = "Ok")
    @ApiResponse(responseCode = "400", description = "Id inválido")
    @ApiResponse(responseCode = "404", description = "Id não encontrado")
    @Get(value = "/times", produces = {"application/json"})
    public HttpResponse<List<TimeApiResponse>> getAllTimes() {
        var response = service.getAll();

        return isEmpty(response) ? notFound() : ok(response);
    }

    @Operation(summary = "Busca um time for id", operationId = "getTimeById", description = "Retorna as informações de um time", tags = {"Times"})
    @ApiResponse(responseCode = "200", description = "Ok")
    @ApiResponse(responseCode = "400", description = "Id inválido")
    @ApiResponse(responseCode = "404", description = "Id não encontrado")
    @Get(value = "/times/{id}", produces = {"application/json"})
    public HttpResponse<TimeApiResponse> getTimeById(@PathVariable("id") Long id) {
        var response = service.getById(id);

        return isNull(response) ? notFound() : ok(response);
    }

    @Operation(summary = "Cadastrar um time", operationId = "postTime", description = "Registra um novo time", tags = {"Times"})
    @ApiResponse(responseCode = "201", description = "Criado")
    @ApiResponse(responseCode = "422", description = "Erro")
    @ApiResponse(responseCode = "500", description = "Erro interno")
    @Post(value = "/times", produces = {"application/json"}, consumes = {"application/json"})
    public HttpResponse<Void> postTime(@Valid @Body TimeApiRequest request) {
        validate(request);

        var inputParams = buildParamsTime(request);

        service.save(inputParams);

        return noContent();
    }

    @Operation(summary = "Alterar um time", operationId = "putTime", description = "Modifica as informações de um time", tags = {"Times"})
    @ApiResponse(responseCode = "204", description = "Operação bem sucedida")
    @ApiResponse(responseCode = "400", description = "Id inválido")
    @ApiResponse(responseCode = "404", description = "Id não encontrado")
    @Put(value = "/times/{id}", produces = {"application/json"}, consumes = {"application/json"})
    public HttpResponse<Void> putTime(@Valid @Body TimeApiRequest request, @PathVariable("id") Long id) {
        validate(request);

        var inputParams = buildParamsTime(request);

        service.updateById(id, inputParams);

        return noContent();
    }
}
