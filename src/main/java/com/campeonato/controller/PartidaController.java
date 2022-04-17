package com.campeonato.controller;

import campeonato.controller.PartidasApi;
import campeonato.model.PartidaApiRequest;
import com.campeonato.controller.factory.CampeonatoApiFactory;
import com.campeonato.domain.service.PartidaService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.reactivex.Single;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.campeonato.controller.factory.CampeonatoApiFactory.*;
import static com.campeonato.controller.validator.CampeonatoApiValidator.*;
import static io.micronaut.core.util.CollectionUtils.isEmpty;
import static io.micronaut.http.HttpResponse.*;
import static io.reactivex.Single.just;

@Controller
@RequiredArgsConstructor
public class PartidaController implements PartidasApi {

    private final PartidaService service;

    @Override
    public Single<HttpResponse<List>> getAllPartidas() {
        var response = service.getAll();

        return isEmpty(response) ? just(notFound()) : just(ok(response));
    }

    @Override
    public Single<HttpResponse<Void>> postAcrescimo(Long idTorneio, Long idPartida, Integer tempo, Integer minutos) {
        var inputParams = CampeonatoApiFactory.buildParamsEventoAcrescimo(idTorneio, idPartida, tempo, minutos);

        service.post(inputParams);

        return just(noContent());
    }

    @Override
    public Single<HttpResponse<Void>> postAdvertencia(Long idTorneio, Long idPartida, String tipo, Long idJogador) {
        var inputParams = CampeonatoApiFactory.buildParamsEventoAdvertencia(idTorneio, idPartida, tipo, idJogador);

        service.post(inputParams);

        return just(noContent());
    }

    @Override
    public Single<HttpResponse<Void>> postFim(Long idTorneio, Long idPartida, String dataFim) {
        validateDataFim(dataFim);

        var inputParams = CampeonatoApiFactory.buildParamsEventoInicioFim(idTorneio, idPartida, dataFim);

        service.post(inputParams);

        return just(noContent());
    }

    @Override
    public Single<HttpResponse<Void>> postGol(Long idTorneio, Long idPartida, Integer minuto, Long idJogador) {
        var inputParams = CampeonatoApiFactory.buildParamsEventoGol(idTorneio, idPartida, minuto, idJogador);

        service.post(inputParams);

        return just(noContent());
    }

    @Override
    public Single<HttpResponse<Void>> postInicio(Long idTorneio, Long idPartida, String dataInicio) {
        validateDataInicio(dataInicio);

        var inputParams = buildParamsEventoInicioFim(idTorneio, idPartida, dataInicio);

        service.post(inputParams);

        return just(noContent());
    }

    @Override
    public Single<HttpResponse<Void>> postPartida(PartidaApiRequest request) {
        validate(request);

        var inputParams = buildParamsEvento(request);

        service.post(inputParams);

        return just(noContent());
    }

    @Override
    public Single<HttpResponse<Void>> postSubstituicao(Long idTorneio, Long idPartida, Long idJogadorSubstituido, Long idJogadorSubstituto, Integer minuto) {
        var inputParams = buildParamsEventoSubtituicao(idTorneio, idPartida, idJogadorSubstituido, idJogadorSubstituto, minuto);

        service.post(inputParams);

        return just(noContent());
    }
}
