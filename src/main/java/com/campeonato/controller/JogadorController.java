package com.campeonato.controller;

import campeonato.controller.JogadoresApi;
import campeonato.model.JogadorApiRequest;
import campeonato.model.JogadorApiResponse;
import com.campeonato.controller.factory.CampeonatoApiFactory;
import com.campeonato.domain.service.JogadorService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.reactivex.Single;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static io.micronaut.core.util.CollectionUtils.isEmpty;
import static io.micronaut.http.HttpResponse.*;
import static io.reactivex.Single.just;
import static java.util.Objects.isNull;

@Controller
@RequiredArgsConstructor
public class JogadorController implements JogadoresApi {

    private final JogadorService jogadorService;

    @Override
    public Single<HttpResponse<Void>> deleteJogador(Long id) {
        jogadorService.deleteJogador(id);

        return just(ok());
    }

    @Override
    public Single<HttpResponse<List>> getAllJogadores() {
        var response = jogadorService.getAll();

        return isEmpty(response) ? just(notFound()) : just(ok(response));
    }

    @Override
    public Single<HttpResponse<JogadorApiResponse>> getJogadorById(Long id) {
        var response = jogadorService.getById(id);

        return isNull(response) ? just(notFound()) : just(ok(response));
    }

    @Override
    public Single<HttpResponse<Void>> postJogador(JogadorApiRequest request) {
        var inputParams = CampeonatoApiFactory.buildParamsJogador(request);

        jogadorService.post(inputParams);

        return just(noContent());
    }

    @Override
    public Single<HttpResponse<Void>> putJogador(JogadorApiRequest request, Long id) {
        var inputParams = CampeonatoApiFactory.buildParamsJogador(request);

        jogadorService.put(id, inputParams);

        return just(noContent());
    }
}
