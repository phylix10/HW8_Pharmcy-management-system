create database Pharmacy_management_system;


create type roles as enum ('ADMIN', 'PATIENT', 'SUPER_ADMIN');


create table admin
(
    id            serial primary key,
    name          varchar,
    username      varchar unique,
    password      text,
    age           int,
    national_code varchar unique,
    role          roles
);

create table patient
(
    id            serial primary key,
    name          varchar,
    username      varchar unique,
    password      text,
    age           int,
    national_code varchar unique,
    role          roles default 'PATIENT'
);

create table prescription
(
    id           serial primary key,
    patient_id   int,
    is_confirmed bool default false,
    total_price  int,
    constraint fk_patient_id
        foreign key (patient_id)
            references patient (id)
);

create table item
(
    id      serial primary key,
    name    varchar unique,
    is_exist bool,
    price   int
);

create table prescription_item
(
    id              serial primary key,
    prescription_id int,
    item_id         int,
    count_item      int,
    constraint fk_prescription_id
        foreign key (prescription_id)
            references prescription (id),
    constraint fk_item_id
        foreign key (item_id)
            references item (id)
);

