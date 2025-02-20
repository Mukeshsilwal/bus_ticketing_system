-- Migration script for creating tables

CREATE TABLE ticket.bus_stop (
                                 id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                 name VARCHAR(255) NOT NULL
);

CREATE TABLE ticket.route (
                              id BIGINT AUTO_INCREMENT PRIMARY KEY,
                              source_id BIGINT,
                              destination_id BIGINT,
                              FOREIGN KEY (source_id) REFERENCES ticket.bus_stop(id),
                              FOREIGN KEY (destination_id) REFERENCES ticket.bus_stop(id)
);

CREATE TABLE ticket.bus (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            route_id BIGINT,
                            FOREIGN KEY (route_id) REFERENCES ticket.route(id)
);

CREATE TABLE ticket.seat (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             reserved BOOLEAN NOT NULL,
                             price DECIMAL(10, 2) NOT NULL,
                             seat_number VARCHAR(255) NOT NULL,
                             bus_id BIGINT,
                             FOREIGN KEY (bus_id) REFERENCES ticket.bus(id)
);

CREATE TABLE ticket.ticket (
                               ticket_no BIGINT AUTO_INCREMENT PRIMARY KEY,
                               seat_number VARCHAR(255) UNIQUE NOT NULL,
                               seat_id BIGINT,
                               booking_id BIGINT,
                               FOREIGN KEY (seat_id) REFERENCES ticket.seat(id),
                               FOREIGN KEY (booking_id) REFERENCES ticket.booking_ticket(id)
);

CREATE TABLE ticket.booked (
                               id INT AUTO_INCREMENT PRIMARY KEY,
                               seat_id BIGINT,
                               FOREIGN KEY (seat_id) REFERENCES ticket.seat(id)
);

CREATE TABLE ticket.booking_ticket (
                                       id BIGINT AUTO_INCREMENT PRIMARY KEY
);

CREATE TABLE ticket.users_table (
                                    id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                    username VARCHAR(255) UNIQUE NOT NULL,
                                    password VARCHAR(255) NOT NULL,
                                    email VARCHAR(255) NOT NULL,
                                    role VARCHAR(255) NOT NULL
);
