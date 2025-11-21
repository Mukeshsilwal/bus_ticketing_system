package com.Transaction.transaction.repository;

import com.Transaction.transaction.entity.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface BusRepo extends JpaRepository<Bus,Long> {
    List<Bus> findByRouteSourceBusStopNameAndRouteDestinationBusStopNameAndDepartureDate(
            String source, String destination, LocalDate time);


}
