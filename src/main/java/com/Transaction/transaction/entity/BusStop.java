package com.Transaction.transaction.entity;

import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString(exclude = {"sourceRoutes", "destinationRoutes"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "bus_stop",
        indexes = @Index(name = "idx_bus_stop_name", columnList = "name"))
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BusStop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotBlank(message = "Bus stop name is required")
    @Size(max = 100, message = "Bus stop name must be less than 100 characters")
    @Column(nullable = false, unique = true, length = 100)
    private String name;

    @OneToMany(
            mappedBy = "sourceBusStop",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<Route> sourceRoutes = new ArrayList<>();

    @OneToMany(
            mappedBy = "destinationBusStop",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<Route> destinationRoutes = new ArrayList<>();

    public void addSourceRoute(Route route) {
        if (!sourceRoutes.contains(route)) {
            sourceRoutes.add(route);
            route.setSourceBusStop(this);
        }
    }

    public void removeSourceRoute(Route route) {
        if (sourceRoutes.remove(route)) {
            route.setSourceBusStop(null);
        }
    }

    public void addDestinationRoute(Route route) {
        if (!destinationRoutes.contains(route)) {
            destinationRoutes.add(route);
            route.setDestinationBusStop(this);
        }
    }

    public void removeDestinationRoute(Route route) {
        if (destinationRoutes.remove(route)) {
            route.setDestinationBusStop(null);
        }
    }
}
