INSERT INTO `Asset` (`assetCode`, `assetName`, `description`) VALUES ('BTC', 'Bitcoin', 'Beschrijving');
INSERT INTO `Asset` (`assetCode`, `assetName`, `description`) VALUES ('ETH', 'Ethereum', 'Beschrijving');
INSERT INTO `Asset` (`assetCode`, `assetName`, `description`) VALUES ('ADA', 'Cardano', 'Beschrijving');
INSERT INTO `Asset` (`assetCode`, `assetName`, `description`) VALUES ('BNB', 'Binance Coin', 'Beschrijving');
INSERT INTO `Asset` (`assetCode`, `assetName`, `description`) VALUES ('USDT', 'Tether', 'Beschrijving');
INSERT INTO `Asset` (`assetCode`, `assetName`, `description`) VALUES ('XRP', 'XRP', 'Beschrijving');
INSERT INTO `Asset` (`assetCode`, `assetName`, `description`) VALUES ('DOGE', 'Dogecoin', 'Beschrijving');
INSERT INTO `Asset` (`assetCode`, `assetName`, `description`) VALUES ('SOL', 'Solana', 'Beschrijving');
INSERT INTO `Asset` (`assetCode`, `assetName`, `description`) VALUES ('DOT', 'Poldakot', 'Beschrijving');
INSERT INTO `Asset` (`assetCode`, `assetName`, `description`) VALUES ('USDC', 'USD Coin', 'Beschrijving');
INSERT INTO `Asset` (`assetCode`, `assetName`, `description`) VALUES ('UNI', 'Uniswap', 'Beschrijving');
INSERT INTO `Asset` (`assetCode`, `assetName`, `description`) VALUES ('LINK', 'Chainlink', 'Beschrijving');
INSERT INTO `Asset` (`assetCode`, `assetName`, `description`) VALUES ('LUNA', 'Terra', 'Beschrijving');
INSERT INTO `Asset` (`assetCode`, `assetName`, `description`) VALUES ('BCH', 'Bitcoin Cash', 'Beschrijving');
INSERT INTO `Asset` (`assetCode`, `assetName`, `description`) VALUES ('BUSD', 'Binance USD', 'Beschrijving');
INSERT INTO `Asset` (`assetCode`, `assetName`, `description`) VALUES ('LTC', 'Litecoin', 'Beschrijving');
INSERT INTO `Asset` (`assetCode`, `assetName`, `description`) VALUES ('ICP', 'Internet Computer', 'Beschrijving');
INSERT INTO `Asset` (`assetCode`, `assetName`, `description`) VALUES ('WBTC', 'Wrapped Bitcoin', 'Beschrijving');
INSERT INTO `Asset` (`assetCode`, `assetName`, `description`) VALUES ('MATIC', 'Polygon', 'Beschrijving');
INSERT INTO `Asset` (`assetCode`, `assetName`, `description`) VALUES ('VET', 'VeChain', 'Beschrijving');


INSERT INTO `AssetPrice` (`assetCode`, `datetime`, `price`) VALUES ('ETH', now(), 3965.20);
INSERT INTO `AssetPrice` (`assetCode`, `datetime`, `price`) VALUES ('BTC', now(), 42273.5);
INSERT INTO `AssetPrice` (`assetCode`, `datetime`, `price`) VALUES ('DOGE', now(), 25.75);
INSERT INTO `AssetPrice` (`assetCode`, `datetime`, `price`) VALUES ('ADA', now(), 30.75);
INSERT INTO `AssetPrice` (`assetCode`, `datetime`, `price`) VALUES ('BNB', now(), 102.56);
INSERT INTO `AssetPrice` (`assetCode`, `datetime`, `price`) VALUES ('USDT', now(), 203.78);
INSERT INTO `AssetPrice` (`assetCode`, `datetime`, `price`) VALUES ('XRP', now(), 405.68);
INSERT INTO `AssetPrice` (`assetCode`, `datetime`, `price`) VALUES ('SOL', now(), 35.90);
INSERT INTO `AssetPrice` (`assetCode`, `datetime`, `price`) VALUES ('DOT', now(), 36.89);
INSERT INTO `AssetPrice` (`assetCode`, `datetime`, `price`) VALUES ('USDC', now(), 3003.45);
INSERT INTO `AssetPrice` (`assetCode`, `datetime`, `price`) VALUES ('UNI', now(), 100.45);
INSERT INTO `AssetPrice` (`assetCode`, `datetime`, `price`) VALUES ('LINK', now(), 200.70);
INSERT INTO `AssetPrice` (`assetCode`, `datetime`, `price`) VALUES ('LUNA', now(), 345.67);
INSERT INTO `AssetPrice` (`assetCode`, `datetime`, `price`) VALUES ('BCH', now(), 654.70);
INSERT INTO `AssetPrice` (`assetCode`, `datetime`, `price`) VALUES ('BUSD', now(), 123.89);
INSERT INTO `AssetPrice` (`assetCode`, `datetime`, `price`) VALUES ('LTC', now(), 321.65);
INSERT INTO `AssetPrice` (`assetCode`, `datetime`, `price`) VALUES ('ICP', now(), 2008.90);
INSERT INTO `AssetPrice` (`assetCode`, `datetime`, `price`) VALUES ('WBTC', now(), 28.54);
INSERT INTO `AssetPrice` (`assetCode`, `datetime`, `price`) VALUES ('MATIC', now(), 80.90);
INSERT INTO `AssetPrice` (`assetCode`, `datetime`, `price`) VALUES ('VET', now(), 76.89);

insert into portfolio (username, assetCode, amount)
values ('TestUser100', 'MATIC', 1.00);
insert into portfolio (username, assetCode, amount)
values ('TestUser100', 'DOGE', 357.00);
insert into portfolio (username, assetCode, amount)
values ('TestUser101', 'MATIC', 1.00);
insert into portfolio (username, assetCode, amount)
values ('TestUser102', 'MATIC', 1.00);
insert into portfolio (username, assetCode, amount)
values ('TestUser103', 'MATIC', 1.00);
insert into portfolio (username, assetCode, amount)
values ('TestUser103', 'ETH', 159.00);
insert into portfolio (username, assetCode, amount)
values ('TestUser103', 'BTC', 753.00);
insert into portfolio (username, assetCode, amount)
values ('TestUser104', 'MATIC', 1.00);
insert into portfolio (username, assetCode, amount)
values ('TestUser105', 'MATIC', 1.00);
insert into portfolio (username, assetCode, amount)
values ('TestUser106', 'MATIC', 1.00);
insert into portfolio (username, assetCode, amount)
values ('TestUser107', 'MATIC', 1.00);
insert into portfolio (username, assetCode, amount)
values ('TestUser108', 'MATIC', 1.00);
insert into portfolio (username, assetCode, amount)
values ('TestUser109', 'MATIC', 1.00);
insert into portfolio (username, assetCode, amount)
values ('TestUser109', 'BTV', 1.00);
insert into portfolio (username, assetCode, amount)
values ('TestUser110', 'MATIC', 1.00);
insert into portfolio (username, assetCode, amount)
values ('TestUser111', 'BTC', 1.00);
insert into portfolio (username, assetCode, amount)
values ('TestUser111', 'ETH', 1.00);
insert into portfolio (username, assetCode, amount)
values ('TestUser111', 'MATIC', 1.00);
insert into portfolio (username, assetCode, amount)
values ('TestUser111', 'DOGE', 1.00);

insert into portfoliohistory (userName, date, totalValue)
VALUES ('TestUser105', '2021-09-13', 100.00);
insert into portfoliohistory (userName, date, totalValue)
VALUES ('TestUser105', '2021-09-12', 75.00);
insert into portfoliohistory (userName, date, totalValue)
VALUES ('TestUser105', '2021-09-11', 120.00);
insert into portfoliohistory (userName, date, totalValue)
VALUES ('TestUser106', '2021-09-12', 1.00);
insert into portfoliohistory (userName, date, totalValue)
VALUES ('TestUser107', '2021-09-4', 254159.50);
insert into portfoliohistory (userName, date, totalValue)
VALUES ('TestUser107', '2021-09-3', 250.00);
insert into portfoliohistory (userName, date, totalValue)
VALUES ('TestUser107', '2021-09-2', 1.00);

INSERT INTO `transaction` (`id`, `assetCode`, `ibanSell`, `ibanBuy`, `amount`, `sellingPrice`, `transactionCost`, `date`) VALUES (1,'BTC','NL24DIGO1111111111','NL24DIGO2222222222',1,1000,0.01,'2020-01-01');
INSERT INTO `transaction` (`id`, `assetCode`, `ibanSell`, `ibanBuy`, `amount`, `sellingPrice`, `transactionCost`, `date`) VALUES (2,'ETH','NL24DIGO2222222222','NL24DIGO3333333333',2,1000,0.01,'2020-02-01');
INSERT INTO `transaction` (`id`, `assetCode`, `ibanSell`, `ibanBuy`, `amount`, `sellingPrice`, `transactionCost`, `date`) VALUES (3,'ADA','NL24DIGO2222222222','NL24DIGO4444444444',3,1500,0.02,'2020-03-01');

insert into customer (username, password, firstName, prefix, lastName, dateOfBirth, bsn, houseNumber, streetName,
                      zipCode, city, emailAddress, salt, status, iban)
values ('TestUser100','TestPassword', 'JanTester', 'van', 'JanssenTester', '1950-01-01', '753654850', '1', 'TestStraat', '1111AA',
        'TestCity', 'tester100@gmail.com', 'testzoutje',true, 'NL10DIGO9876543210');
insert into customer (username, password, firstName, prefix, lastName, dateOfBirth, bsn, houseNumber, streetName,
                      zipCode, city, emailAddress, salt, status, iban)
values ('TestUser101','TestPassword', 'JanTester', 'van', 'JanssenTester', '1950-01-01', '753654851', '1', 'TestStraat', '1111AA',
        'TestCity', 'tester101@gmail.com', 'testzoutje',true, 'NL10DIGO9876543211');
insert into customer (username, password, firstName, prefix, lastName, dateOfBirth, bsn, houseNumber, streetName,
                      zipCode, city, emailAddress, salt, status, iban)
values ('TestUser102','TestPassword', 'JanTester', 'van', 'JanssenTester', '1950-01-01', '753654852', '1', 'TestStraat', '1111AA',
        'TestCity', 'tester102@gmail.com', 'testzoutje',true, 'NL10DIGO9876543212');
insert into customer (username, password, firstName, prefix, lastName, dateOfBirth, bsn, houseNumber, streetName,
                      zipCode, city, emailAddress, salt, status, iban)
values ('TestUser103','TestPassword', 'JanTester', 'van', 'JanssenTester', '1950-01-01', '753654853', '1', 'TestStraat', '1111AA',
        'TestCity', 'tester103@gmail.com', 'testzoutje',true, 'NL10DIGO9876543213');
insert into customer (username, password, firstName, prefix, lastName, dateOfBirth, bsn, houseNumber, streetName,
                      zipCode, city, emailAddress, salt, status, iban)
values ('TestUser104','TestPassword', 'JanTester', 'van', 'JanssenTester', '1950-01-01', '753654854', '1', 'TestStraat', '1111AA',
        'TestCity', 'tester104@gmail.com', 'testzoutje',true, 'NL10DIGO9876543214');
insert into customer (username, password, firstName, prefix, lastName, dateOfBirth, bsn, houseNumber, streetName,
                      zipCode, city, emailAddress, salt, status, iban)
values ('TestUser105','TestPassword', 'JanTester', 'van', 'JanssenTester', '1950-01-01', '753654855', '1', 'TestStraat', '1111AA',
        'TestCity', 'tester105@gmail.com', 'testzoutje',true, 'NL10DIGO9876543210');
insert into customer (username, password, firstName, prefix, lastName, dateOfBirth, bsn, houseNumber, streetName,
                      zipCode, city, emailAddress, salt, status, iban)
values ('TestUser106','TestPassword', 'JanTester', 'van', 'JanssenTester', '1950-01-01', '753654856', '1', 'TestStraat', '1111AA',
        'TestCity', 'tester106@gmail.com', 'testzoutje',true, 'NL10DIGO9876543210');
insert into customer (username, password, firstName, prefix, lastName, dateOfBirth, bsn, houseNumber, streetName,
                      zipCode, city, emailAddress, salt, status, iban)
values ('TestUser107','TestPassword', 'JanTester', 'van', 'JanssenTester', '1950-01-01', '753654857', '1', 'TestStraat', '1111AA',
        'TestCity', 'tester107@gmail.com', 'testzoutje',true, 'NL10DIGO9876543210');
insert into customer (username, password, firstName, prefix, lastName, dateOfBirth, bsn, houseNumber, streetName,
                      zipCode, city, emailAddress, salt, status, iban)
values ('TestUser108','TestPassword', 'JanTester', 'van', 'JanssenTester', '1950-01-01', '753654858', '1', 'TestStraat', '1111AA',
        'TestCity', 'tester108@gmail.com', 'testzoutje',true, 'NL10DIGO9876543210');
insert into customer (username, password, firstName, prefix, lastName, dateOfBirth, bsn, houseNumber, streetName,
                      zipCode, city, emailAddress, salt, status, iban)
values ('TestUser109','TestPassword', 'JanTester', 'van', 'JanssenTester', '1950-01-01', '753654859', '1', 'TestStraat', '1111AA',
        'TestCity', 'tester109@gmail.com', 'testzoutje',true, 'NL10DIGO9876543210');
insert into customer (username, password, firstName, prefix, lastName, dateOfBirth, bsn, houseNumber, streetName,
                      zipCode, city, emailAddress, salt, status, iban)
values ('TestUser110','TestPassword', 'JanTester', 'van', 'JanssenTester', '1950-01-01', '763654850', '1', 'TestStraat', '1111AA',
        'TestCity', 'tester110@gmail.com', 'testzoutje',true, 'NL10DIGO9876543210');
insert into customer (username, password, firstName, prefix, lastName, dateOfBirth, bsn, houseNumber, streetName,
                      zipCode, city, emailAddress, salt, status, iban)
values ('TestUser111','TestPassword', 'JanTester', 'van', 'JanssenTester', '1950-01-01', '763654851', '1', 'TestStraat', '1111AA',
        'TestCity', 'tester111@gmail.com', 'testzoutje',true, 'NL10DIGO9876543211');
insert into customer (username, password, firstName, prefix, lastName, dateOfBirth, bsn, houseNumber, streetName,
                      zipCode, city, emailAddress, salt, status, iban)
values ('DigitalGoldBank','6fc7fc56ec6007065324f4fd5cb7abc82786b55bb6f12f4c712f750c0db9832f', 'Go', 'for', 'Gold', '1900-01-01', '753654852', '1', 'Golden Alley', '500',
        'BankCity', 'bank@digitalgold.com', '022ba0e4',true, 'NL00DIGO0000000001' );

insert into bankaccount (iban, balance) values ('NL10DIGO9876543211', 38459.69);
insert into bankaccount (iban, balance) values ('NL10DIGO9876543212', 9426.85);
insert into bankaccount (iban, balance) values ('NL10DIGO9876543214', 10385.24);
insert into bankaccount (iban, balance) values ('NL10DIGO9876543215', 305.79);
insert into bankaccount (iban, balance) values ('NL00DIGO0000000001', 5000000.00);

INSERT INTO AdministratorDashboard (userName, startingBudget, transactionFee) VALUES ('DigitalGoldBank', '1250', '0.05');
