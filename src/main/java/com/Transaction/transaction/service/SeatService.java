package com.Transaction.transaction.service;


import com.Transaction.transaction.payloads.SeatDto;

import java.util.List;

public interface SeatService {
    SeatDto updateSeat(SeatDto seatDto, long id);

    void deleteSeat(long id);

    SeatDto getSeatById(long id);

    List<SeatDto> getAllSeat();

    SeatDto createSeatForBus(SeatDto seatDto, long id);

    List<SeatDto> findSeatRelatedToBus(String busName);


}
