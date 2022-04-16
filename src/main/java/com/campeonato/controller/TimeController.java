package com.campeonato.controller;

import campeonato.controller.TimesApi;
import campeonato.model.TimeApiRequest;
import campeonato.model.TimeApiResponse;
import com.campeonato.domain.service.TimeService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.reactivex.Single;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.campeonato.controller.factory.CampeonatoApiFactory.buildParamsTime;
import static io.micronaut.core.util.CollectionUtils.isEmpty;
import static io.micronaut.http.HttpResponse.*;
import static io.reactivex.Single.just;
import static java.util.Objects.isNull;

@Controller
@RequiredArgsConstructor
public class TimeController implements TimesApi {

    private final TimeService service;

    @Override
    public Single<HttpResponse<Void>> deleteTimeById(Long id) {
        service.delete(id);

        return just(ok());
    }

    @Override
    public Single<HttpResponse<List<TimeApiResponse>>> getAllTimes() {
        var response = service.getAll();

        return isEmpty(response) ? just(notFound()) : just(ok(response));
    }

    @Override
    public Single<HttpResponse<TimeApiResponse>> getTimeById(Long id) {
        var response = service.getById(id);

        return isNull(response) ? just(notFound()) : just(ok(response));
    }

    @Override
    public Single<HttpResponse<Void>> postTime(TimeApiRequest request) {
        var inputParams = buildParamsTime(request);

        service.post(inputParams);

        return just(noContent());
    }

    @Override
    public Single<HttpResponse<Void>> putTime(TimeApiRequest request, Long id) {
        var inputParams = buildParamsTime(request);

        service.put(id, inputParams);

        return just(noContent());
    }
}
