package com.Transaction.transaction.repository;

import com.Transaction.transaction.entity.BookingRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Repository
public interface BookingRequestRepo extends JpaRepository<BookingRequest, Integer> {

    void deleteBySeatTicketTicketNoAndSeatTicketBookingTicketEmail(
            long ticketNo, String email);
}




