-- Migration script for creating tables in the default schema

-- Create bus_stops table (note plural name)
CREATE TABLE IF NOT EXISTS bus_stops (
                                         id BIGSERIAL PRIMARY KEY,
                                         name VARCHAR(255) NOT NULL
    );

-- Create route table
CREATE TABLE IF NOT EXISTS route (
                                     id BIGSERIAL PRIMARY KEY,
                                     source_id BIGINT,
                                     destination_id BIGINT,
                                     FOREIGN KEY (source_id) REFERENCES bus_stops(id),
    FOREIGN KEY (destination_id) REFERENCES bus_stops(id)
    );

-- Create bus table
CREATE TABLE IF NOT EXISTS bus (
                                   id BIGSERIAL PRIMARY KEY,
                                   bus_name VARCHAR(255) NOT NULL,
    bus_type VARCHAR(255) NOT NULL,
    departure_date_time TIMESTAMP,      -- for LocalDateTime
    departure_date DATE,                -- for LocalDate
    base_price DECIMAL(10, 2),
    max_price DECIMAL(10, 2),
    fid BIGINT,                         -- join column for Route
    FOREIGN KEY (fid) REFERENCES route(id)
    );

-- Create seat table
CREATE TABLE IF NOT EXISTS seat (
                                    id BIGSERIAL PRIMARY KEY,
                                    reserved BOOLEAN NOT NULL,
                                    price DECIMAL(10, 2) NOT NULL,
    seat_number VARCHAR(255) NOT NULL,
    bus_id BIGINT,
    FOREIGN KEY (bus_id) REFERENCES bus(id)
    );

-- Create booking_ticket table
CREATE TABLE IF NOT EXISTS booking_ticket (
                                              id BIGSERIAL PRIMARY KEY
);

-- Create ticket table
CREATE TABLE IF NOT EXISTS ticket (
                                      ticket_no BIGSERIAL PRIMARY KEY,
                                      seat_number VARCHAR(255) UNIQUE NOT NULL,
    seat_id BIGINT,
    booking_id BIGINT,
    FOREIGN KEY (seat_id) REFERENCES seat(id),
    FOREIGN KEY (booking_id) REFERENCES booking_ticket(id)
    );

-- Create booked table
CREATE TABLE IF NOT EXISTS booked (
                                      id BIGSERIAL PRIMARY KEY,
                                      seat_id BIGINT,
                                      FOREIGN KEY (seat_id) REFERENCES seat(id)
    );

-- Create users_table table
CREATE TABLE IF NOT EXISTS users_table (
                                           id BIGSERIAL PRIMARY KEY,
                                           username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    role VARCHAR(255) NOT NULL
    );
