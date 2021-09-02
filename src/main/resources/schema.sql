create table Customer
(
    username    varchar(200)   not null    primary key,
    password    varchar(200)   not null,
    firstName    varchar(45)   not null,
    prefix      varchar(10)    null,
    lastName    varchar(200)   not null,
    dateOfBirth varchar(255)   not null,
    bsn         varchar(45)   not null,
    houseNumber varchar(10)   not null,
    streetName varchar(45)    not null,
    zipCode     varchar(10)   not null,
    city       varchar(45)   not null,
    emailAddress       varchar(255)   not null  unique,
    salt       varchar(255),
    status TINYINT NOT NULL,
    iban varchar(9) NOT NULL
    );

create table Administrator
(
    `userName` VARCHAR(200) NOT NULL primary key ,
    `password` VARCHAR(200) NOT NULL,
    `salt` VARCHAR(200) NOT NULL
);

create table BankAccount
(
    `iban` VARCHAR(9) NOT NULL primary key,
    `balance` DECIMAL(2) NOT NULL

);

create table Asset
(
    `assetCode` VARCHAR(9) NOT NULL PRIMARY KEY ,
    `assetName` VARCHAR(45) NOT NULL,
    `description` VARCHAR(500) NOT NULL
);

create table Portfolio
(
    `userName` VARCHAR(200) NOT NULL PRIMARY KEY ,
    `assetCode` VARCHAR(9) NOT NULL,
    `amount` DECIMAL(2) NOT NULL
);

create table Transaction
(
    `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `assetCode` VARCHAR(9) NOT NULL,
    `ibanSell` VARCHAR(9) NOT NULL,
    `ibanBuy` VARCHAR(9) NOT NULL,
    `amount` DECIMAL(2) NOT NULL,
    `sellingPrice` DECIMAL(2) NOT NULL,
    `transactionCost` DECIMAL(2) NOT NULL,
    `date` DATE NOT NULL
);
create table AssetPrice
(
    `assetCode` VARCHAR(9) NOT NULL PRIMARY KEY ,
    `date` DATE NOT NULL,
    `price` DECIMAL(2) NOT NULL
);
create table PortfolioHistory
(
    `userName` VARCHAR(200) NOT NULL PRIMARY KEY ,
    `date` DATE NOT NULL PRIMARY KEY ,
    `totalValue` DECIMAL(2) NOT NULL
);
