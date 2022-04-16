package com.campeonato.controller;

import campeonato.controller.TorneiosApi;
import campeonato.model.TorneioApiRequest;
import campeonato.model.TorneioApiResponse;
import com.campeonato.domain.service.TorneioService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.reactivex.Single;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.campeonato.controller.factory.CampeonatoApiFactory.buildParamsTorneio;
import static io.micronaut.core.util.CollectionUtils.isEmpty;
import static io.micronaut.http.HttpResponse.*;
import static io.reactivex.Single.just;
import static java.util.Objects.isNull;

@Controller
@RequiredArgsConstructor
public class TorneioController implements TorneiosApi {

    private final TorneioService service;

    @Override
    public Single<HttpResponse<List>> getAllTorneios() {
        var response = service.getAll();

        return isEmpty(response) ? just(notFound()) : just(ok(response));
    }

    @Override
    public Single<HttpResponse<TorneioApiResponse>> getTorneioById(Long id) {
        var response = service.getById(id);

        return isNull(response) ? just(notFound()) : just(ok(response));
    }

    @Override
    public Single<HttpResponse<Void>> postTorneio(TorneioApiRequest request) {
        var inputParams = buildParamsTorneio(request);

        service.post(inputParams);

        return just(noContent());
    }

    @Override
    public Single<HttpResponse<Void>> putTorneioById(TorneioApiRequest request, Long id) {
        var inputParams = buildParamsTorneio(request);

        service.put(id, inputParams);

        return just(noContent());
    }
}
