package com.Transaction.transaction.service;

import com.Transaction.transaction.payloads.BusDto;

import java.time.LocalDate;
import java.util.List;

public interface BusService {
    BusDto updateBusInfo(BusDto busDto, long id, long routeId);

    void deleteBusInfo(long id);

    BusDto createBusForRoute(BusDto busDto, long id);

    List<BusDto> getAllBusInfo();

    List<BusDto> getBusByRoute(String source, String destination, LocalDate time);
}
