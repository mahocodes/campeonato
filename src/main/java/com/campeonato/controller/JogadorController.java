package com.campeonato.controller;

import campeonato.controller.JogadoresApi;
import campeonato.model.JogadorApiRequest;
import campeonato.model.JogadorApiResponse;
import com.campeonato.domain.service.JogadorService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.reactivex.Single;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.campeonato.controller.factory.CampeonatoApiFactory.buildParamsJogador;
import static io.micronaut.core.util.CollectionUtils.isEmpty;
import static io.micronaut.http.HttpResponse.*;
import static io.reactivex.Single.just;
import static java.util.Objects.isNull;

@Controller
@RequiredArgsConstructor
public class JogadorController implements JogadoresApi {

    private final JogadorService service;

    @Override
    public Single<HttpResponse<Void>> deleteJogadorById(Long id) {
        service.delete(id);

        return just(ok());
    }

    @Override
    public Single<HttpResponse<List>> getAllJogadores() {
        var response = service.getAll();

        return isEmpty(response) ? just(notFound()) : just(ok(response));
    }

    @Override
    public Single<HttpResponse<JogadorApiResponse>> getJogadorById(Long id) {
        var response = service.getById(id);

        return isNull(response) ? just(notFound()) : just(ok(response));
    }

    @Override
    public Single<HttpResponse<Void>> postJogador(JogadorApiRequest request) {
        var inputParams = buildParamsJogador(request);

        service.post(inputParams);

        return just(noContent());
    }

    @Override
    public Single<HttpResponse<Void>> putJogador(JogadorApiRequest request, Long id) {
        var inputParams = buildParamsJogador(request);

        service.put(id, inputParams);

        return just(noContent());
    }
}
