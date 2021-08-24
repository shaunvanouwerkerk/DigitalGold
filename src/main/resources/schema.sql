create schema if not exists `digitalgolddb`;

DROP table if exists `digitalgolddb`.`customer_table`;

create table if not exists `digitalgolddb`. `customer_table`
(
    username    varchar(255)   not null    primary key,
    password    varchar(255)   not null,
    firstname    varchar(255)   not null,
    prefix      varchar(10),
    lastName    varchar(255)   not null,
    dateOfBirth varchar(255)   not null,
    bsn         int(255)        not null,
    houseNumber varchar(255)   not null,
    streetname varchar(255)    not null,
    zipCode     varchar(255)   not null,
    city       varchar(255)   not null,
    emailAddress       varchar(255)   not null,
    salt       varchar(255)

    );

