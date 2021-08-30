create table customer_table
(
    username    varchar(255)   not null    primary key,
    password    varchar(255)   not null,
    firstName    varchar(255)   not null,
    prefix      varchar(10),
    lastName    varchar(255)   not null,
    dateOfBirth varchar(255)   not null,
    bsn         varchar(255)   not null,
    houseNumber varchar(255)   not null,
    streetName varchar(255)    not null,
    zipCode     varchar(255)   not null,
    city       varchar(255)   not null,
    emailAddress       varchar(255)   not null  unique,
    salt       varchar(255)

    );

