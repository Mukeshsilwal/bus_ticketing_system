package com.Transaction.transaction.service;

import com.Transaction.transaction.entity.Ticket;
import com.Transaction.transaction.payloads.TicketDto;

import java.io.IOException;
import java.util.List;

public interface TicketService {

    TicketDto updateTicket(TicketDto ticketDto, long tId);

    TicketDto createSeatWithTicket(TicketDto ticketDto, long id, long bookId);

    void deleteSeatWithTicket(long tId);

    Ticket getTicketById(long tId);

    void sendBookingConfirmationEmail(String userEmail, byte[] pdfContent) throws IOException;
}
