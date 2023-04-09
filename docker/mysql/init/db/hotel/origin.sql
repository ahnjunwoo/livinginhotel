CREATE SCHEMA IF NOT EXISTS `hotel`;
use hotel;


-- hotel.booking definition

create table booking
(
    id               bigint       not null auto_increment,
    hotel_id         bigint       not null,
    status           varchar(255) not null,
    create_date_time datetime(6),
    update_date_time datetime(6),
    primary key (id)
) engine=InnoDB;

-- hotel.hotel definition
create table hotel
(
    id               bigint       not null auto_increment,
    name             varchar(255) not null,
    room_quantity    integer,
    status           varchar(255) not null,
    version          bigint,
    create_date_time datetime(6),
    update_date_time datetime(6),
    primary key (id)
) engine=InnoDB;