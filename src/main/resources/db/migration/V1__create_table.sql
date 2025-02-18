create table book
(
    id        int auto_increment
        primary key,
    email     varchar(255) null,
    full_name varchar(255) null,
    id1       int          null,
    constraint FK2ssno6ketyyvlx6pn0txc4gmv
        foreign key (id1) references users_table (id1)
);

create table booked
(
    id  int auto_increment
        primary key,
    id1 int null,
    constraint FKjcrt4h45o29u7cgf5r4x6ak8i
        foreign key (id1) references seat (id)
);

create table bus
(
    id                  int auto_increment
        primary key,
    base_price          decimal(19, 2) null,
    bus_name            varchar(255)   null,
    bus_type            varchar(255)   null,
    date                date           null,
    departure_date_time datetime(6)    null,
    max_price           decimal(19, 2) null,
    fid                 int            null,
    constraint FKrpfnnbv6xvkycs0b8qa27pmu5
        foreign key (fid) references route (id)
);

create table bus_stop
(
    id   int          not null
        primary key,
    name varchar(255) null
);

create table route
(
    id             int auto_increment
        primary key,
    destination_id int null,
    source_id      int null,
    constraint FKeyocaey2le8eepmsjfqh336vt
        foreign key (destination_id) references bus_stop (id),
    constraint FKs05x0clj128blq2lrqew9k4d0
        foreign key (source_id) references bus_stop (id)
);

create table seat
(
    id          int auto_increment
        primary key,
    price       decimal(19, 2) null,
    reserved    bit            not null,
    seat_number varchar(255)   null,
    fid         int            null,
    constraint FKkacxkojdc05klccwtc9st8681
        foreign key (fid) references bus (id)
);

create table ticket
(
    ticket_no   int auto_increment
        primary key,
    seat_number varchar(255) null,
    booking_id  int          null,
    seat_id     int          null,
    constraint UK_jccoc7noiiqqdxqptqdh76iox
        unique (seat_number),
    constraint FKdmtaw308drxfqq4j7ew4ynvjw
        foreign key (booking_id) references book (id),
    constraint FKqahao9a85drt47ikjp0b8syvh
        foreign key (seat_id) references seat (id)
);

create table users_table
(
    id1      int          not null
        primary key,
    email    varchar(255) null,
    otp      varchar(255) null,
    password varchar(255) null,
    role     int          null,
    role1    varchar(255) null
);