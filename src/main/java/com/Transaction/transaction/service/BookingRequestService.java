package com.Transaction.transaction.service;

import com.Transaction.transaction.model.ReservationResponse;
import com.Transaction.transaction.payloads.BookingRequestDto;

import java.time.LocalDateTime;
import java.util.Date;

public interface BookingRequestService {
    ReservationResponse rserveSeat(BookingRequestDto requestDto, long seatID);

    void cancelReservation(String email, long ticketNo, long bookingId);

    void cancelNotification(String email);
}
