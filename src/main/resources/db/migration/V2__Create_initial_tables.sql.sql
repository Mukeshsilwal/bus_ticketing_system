-- Migration script for creating tables if they do not exist

-- Create bus_stop table if it does not exist
CREATE TABLE IF NOT EXISTS bus_stop (
                                        id BIGSERIAL PRIMARY KEY,
                                        name VARCHAR(255) NOT NULL
    );

-- Create route table if it does not exist
CREATE TABLE IF NOT EXISTS route (
                                     id BIGSERIAL PRIMARY KEY,
                                     source_id BIGINT,
                                     destination_id BIGINT,
                                     FOREIGN KEY (source_id) REFERENCES ticket.bus_stop(id),
    FOREIGN KEY (destination_id) REFERENCES ticket.bus_stop(id)
    );

-- Create bus table if it does not exist
CREATE TABLE IF NOT EXISTS bus (
                                   id BIGSERIAL PRIMARY KEY,
                                   route_id BIGINT,
                                   FOREIGN KEY (route_id) REFERENCES ticket.route(id)
    );

-- Create seat table if it does not exist
CREATE TABLE IF NOT EXISTS seat (
                                    id BIGSERIAL PRIMARY KEY,
                                    reserved BOOLEAN NOT NULL,
                                    price DECIMAL(10, 2) NOT NULL,
    seat_number VARCHAR(255) NOT NULL,
    bus_id BIGINT,
    FOREIGN KEY (bus_id) REFERENCES ticket.bus(id)
    );

-- Create ticket table if it does not exist
CREATE TABLE IF NOT EXISTS ticket (
                                      ticket_no BIGSERIAL PRIMARY KEY,
                                      seat_number VARCHAR(255) UNIQUE NOT NULL,
    seat_id BIGINT,
    booking_id BIGINT,
    FOREIGN KEY (seat_id) REFERENCES ticket.seat(id),
    FOREIGN KEY (booking_id) REFERENCES ticket.booking_ticket(id)
    );

-- Create booked table if it does not exist
CREATE TABLE IF NOT EXISTS booked (
                                      id BIGSERIAL PRIMARY KEY,
                                      seat_id BIGINT,
                                      FOREIGN KEY (seat_id) REFERENCES ticket.seat(id)
    );

-- Create booking_ticket table if it does not exist
CREATE TABLE IF NOT EXISTS booking_ticket (
                                              id BIGSERIAL PRIMARY KEY
);

-- Create users_table table if it does not exist
CREATE TABLE IF NOT EXISTS users_table (
                                           id BIGSERIAL PRIMARY KEY, -- Removed quotes for better compatibility
                                           username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    role VARCHAR(255) NOT NULL
    );
