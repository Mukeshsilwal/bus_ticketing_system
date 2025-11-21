package com.Transaction.transaction.service;

import com.Transaction.transaction.payloads.BookingTicketDto;

import java.util.List;

public interface BookingTicketService {
    List<BookingTicketDto> getAllBooking();

    BookingTicketDto getBooking(long bookingId);

    BookingTicketDto createBooking(BookingTicketDto bookingTicketDto);

    BookingTicketDto getBookById(long id);

}
