package com.Transaction.transaction.service;

import com.Transaction.transaction.payloads.BusStopDto;

import java.util.List;

public interface BusStopService {
    BusStopDto createBusStop(BusStopDto busStopDto);

    BusStopDto updateBusStop(BusStopDto busStopDto, long id);

    void deleteBusStop(long id);

    List<BusStopDto> getAllBusStops();

    BusStopDto getBusStopById(long id);
}
