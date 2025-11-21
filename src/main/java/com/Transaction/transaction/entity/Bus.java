package com.Transaction.transaction.entity;

import lombok.*;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "bus")
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String busName;
    private String busType;
    private LocalDateTime departureDateTime;
    private LocalDate departureDate;
    private BigDecimal basePrice;
    private BigDecimal maxPrice;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fid")
    private Route route;

    @OneToMany(mappedBy = "bus", fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},
            orphanRemoval = true)
    private List<Seat> seats = new ArrayList<>();

    public void addSeat(Seat seat) {
        if (!seats.contains(seat)) {
            seats.add(seat);
            seat.setBus(this);
        }
    }

    public void removeSeat(Seat seat) {
        if (seats.remove(seat)) {
            seat.setBus(null);
        }
    }
}
