package com.Transaction.transaction.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long ticketNo;

    @Column(unique = true, nullable = false)
    private String seatNumber;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seat_id", referencedColumnName = "id")
    private Seat seat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id", referencedColumnName = "id")
    private BookingTicket bookingTicket;
}
