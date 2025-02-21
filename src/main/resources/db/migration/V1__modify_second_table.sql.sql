-- Migration script for creating tables if they do not exist in the default schema

-- Create bus_stop table
CREATE TABLE IF NOT EXISTS bus_stop (
                                        id BIGSERIAL PRIMARY KEY,
                                        name VARCHAR(255) NOT NULL
    );

-- Create route table
CREATE TABLE IF NOT EXISTS route (
                                     id BIGSERIAL PRIMARY KEY,
                                     source_id BIGINT,
                                     destination_id BIGINT,
                                     FOREIGN KEY (source_id) REFERENCES bus_stop(id),
    FOREIGN KEY (destination_id) REFERENCES bus_stop(id)
    );

-- Create bus table
CREATE TABLE IF NOT EXISTS bus (
                                   id BIGSERIAL PRIMARY KEY,
                                   route_id BIGINT,
                                   FOREIGN KEY (route_id) REFERENCES route(id)
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
