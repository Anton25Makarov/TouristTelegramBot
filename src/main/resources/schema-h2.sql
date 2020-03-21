create schema "TOURIST";

create table "TOURIST"."CITY"
(
    ID   serial primary key,
    NAME varchar(30) unique not null,
    DESCRIPTION varchar(255)
);
