-- Create schema "ticket" if it does not exist
CREATE SCHEMA IF NOT EXISTS ticket;

-- Migration script for creating tables if they do not exist

-- Create bus_stop table in the ticket schema
CREATE TABLE IF NOT EXISTS ticket.bus_stop (
                                               id BIGSERIAL PRIMARY KEY,
                                               name VARCHAR(255) NOT NULL
    );

-- Create route table in the ticket schema
CREATE TABLE IF NOT EXISTS ticket.route (
                                            id BIGSERIAL PRIMARY KEY,
                                            source_id BIGINT,
                                            destination_id BIGINT,
                                            FOREIGN KEY (source_id) REFERENCES ticket.bus_stop(id),
    FOREIGN KEY (destination_id) REFERENCES ticket.bus_stop(id)
    );

-- Create bus table in the ticket schema
CREATE TABLE IF NOT EXISTS ticket.bus (
                                          id BIGSERIAL PRIMARY KEY,
                                          route_id BIGINT,
                                          FOREIGN KEY (route_id) REFERENCES ticket.route(id)
    );

-- Create seat table in the ticket schema
CREATE TABLE IF NOT EXISTS ticket.seat (
                                           id BIGSERIAL PRIMARY KEY,
                                           reserved BOOLEAN NOT NULL,
                                           price DECIMAL(10, 2) NOT NULL,
    seat_number VARCHAR(255) NOT NULL,
    bus_id BIGINT,
    FOREIGN KEY (bus_id) REFERENCES ticket.bus(id)
    );

-- Create ticket table in the ticket schema
CREATE TABLE IF NOT EXISTS ticket.ticket (
                                             ticket_no BIGSERIAL PRIMARY KEY,
                                             seat_number VARCHAR(255) UNIQUE NOT NULL,
    seat_id BIGINT,
    booking_id BIGINT,
    FOREIGN KEY (seat_id) REFERENCES ticket.seat(id),
    FOREIGN KEY (booking_id) REFERENCES ticket.booking_ticket(id)
    );

-- Create booked table in the ticket schema
CREATE TABLE IF NOT EXISTS ticket.booked (
                                             id BIGSERIAL PRIMARY KEY,
                                             seat_id BIGINT,
                                             FOREIGN KEY (seat_id) REFERENCES ticket.seat(id)
    );

-- Create booking_ticket table in the ticket schema
CREATE TABLE IF NOT EXISTS ticket.booking_ticket (
                                                     id BIGSERIAL PRIMARY KEY
);

-- Create users_table table in the ticket schema
CREATE TABLE IF NOT EXISTS ticket.users_table (
                                                  id BIGSERIAL PRIMARY KEY,
                                                  username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    role VARCHAR(255) NOT NULL
    );
