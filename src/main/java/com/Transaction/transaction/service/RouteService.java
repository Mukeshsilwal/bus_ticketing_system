package com.Transaction.transaction.service;

import com.Transaction.transaction.payloads.RouteDto;

import java.util.List;

public interface RouteService {
    RouteDto updateRoute(RouteDto routeDto, long id);

    void deleteRoute(long id);

    RouteDto getRouteById(long id);

    List<RouteDto> getAllRoute();

    RouteDto createRouteWithBusStop(RouteDto routeDto, long id, long id1);

}
