create schema if not exists extest;

create sequence extest.car_sequence;
alter sequence extest.car_sequence restart with 1;
create table extest.car
(
    id                        bigint not null default nextval('extest.car_sequence'),
    brand                     varchar(255),
    number                    bigint,
    mileage                   int,
    last_service_time         timestamp with time zone,
    primary key (id)
);