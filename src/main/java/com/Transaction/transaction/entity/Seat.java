package com.Transaction.transaction.entity;

import lombok.*;
import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "seat")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private boolean reserved;
    private BigDecimal price;
    private String seatNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fid", referencedColumnName = "id")
    private Bus bus;

    @OneToOne(mappedBy = "seat", fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Ticket ticket;

    @OneToOne(mappedBy = "seat", fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},
            orphanRemoval = true)
    private BookingRequest booking;

    public void cancelRequest() {
        this.reserved = false;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
        if (bus != null && !bus.getSeats().contains(this)) {
            bus.getSeats().add(this);
        }
    }

    public Seat(String seatNumber) {
        this.seatNumber = seatNumber;
    }
}
