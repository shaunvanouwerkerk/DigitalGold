insert into customer (username, password, firstName, prefix, lastName, dateOfBirth, bsn, houseNumber, streetName,
                            zipCode, city, emailAddress, salt, status, iban)
values ('TestUser01','TestPassword', 'Tester', 'van', 'Tester', '1900-01-01', '753654852', '1', 'TestStraat', '1111AA',
        'TestCity', 'tester1@gmail.com', 'testzoutje',true, 'Nl123456789' );

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
INSERT INTO `Asset` (`assetCode`, `assetName`, `description`) VALUES ('WBTC', 'Wrapped Bitcoin', 'Beschrijving ');
INSERT INTO `Asset` (`assetCode`, `assetName`, `description`) VALUES ('MATIC', 'Polygon', 'Beschrijving');
INSERT INTO `Asset` (`assetCode`, `assetName`, `description`) VALUES ('VET', 'VeChain', 'Beschrijving');


INSERT INTO `AssetPrice` (`assetCode`, `date`, `price`) VALUES ('ETH', '2021-09-03', 3965.20);
INSERT INTO `AssetPrice` (`assetCode`, `date`, `price`) VALUES ('ETH', '2021-09-04', 3406.78);
INSERT INTO `AssetPrice` (`assetCode`, `date`, `price`) VALUES ('ETH', '2021-09-05', 12.00);
INSERT INTO `AssetPrice` (`assetCode`, `date`, `price`) VALUES ('BTC', '2021-09-04', 42273.5);
INSERT INTO `AssetPrice` (`assetCode`, `date`, `price`) VALUES ('BTC', '2021-09-05', 5.20);
INSERT INTO `AssetPrice` (`assetCode`, `date`, `price`) VALUES ('DOGE', '2021-09-05', 25.75);
INSERT INTO `AssetPrice` (`assetCode`, `date`, `price`) VALUES ('ADA', '2021-09-05', 30.75);
INSERT INTO `AssetPrice` (`assetCode`, `date`, `price`) VALUES ('BNB', '2021-09-05', 102.56);
INSERT INTO `AssetPrice` (`assetCode`, `date`, `price`) VALUES ('USDT', '2021-09-05', 203.78);
INSERT INTO `AssetPrice` (`assetCode`, `date`, `price`) VALUES ('XRP', '2021-09-05', 405.68);
INSERT INTO `AssetPrice` (`assetCode`, `date`, `price`) VALUES ('SOL', '2021-09-05', 35.90);
INSERT INTO `AssetPrice` (`assetCode`, `date`, `price`) VALUES ('DOT', '2021-09-05', 36.89);
INSERT INTO `AssetPrice` (`assetCode`, `date`, `price`) VALUES ('USDC', '2021-09-05', 3003.45);
INSERT INTO `AssetPrice` (`assetCode`, `date`, `price`) VALUES ('UNI', '2021-09-05', 100.45);
INSERT INTO `AssetPrice` (`assetCode`, `date`, `price`) VALUES ('LINK', '2021-09-05', 200.70);
INSERT INTO `AssetPrice` (`assetCode`, `date`, `price`) VALUES ('LUNA', '2021-09-05', 345.67);
INSERT INTO `AssetPrice` (`assetCode`, `date`, `price`) VALUES ('BCH', '2021-09-05', 654.70);
INSERT INTO `AssetPrice` (`assetCode`, `date`, `price`) VALUES ('BUSD', '2021-09-05', 123.89);
INSERT INTO `AssetPrice` (`assetCode`, `date`, `price`) VALUES ('LTC', '2021-09-05', 321.65);
INSERT INTO `AssetPrice` (`assetCode`, `date`, `price`) VALUES ('ICP', '2021-09-05', 2008.90);
INSERT INTO `AssetPrice` (`assetCode`, `date`, `price`) VALUES ('WBTC', '2021-09-05', 28.54);
INSERT INTO `AssetPrice` (`assetCode`, `date`, `price`) VALUES ('MATIC', '2021-09-05', 80.90);
INSERT INTO `AssetPrice` (`assetCode`, `date`, `price`) VALUES ('VET', '2021-09-05', 76.89);
INSERT INTO `AssetPrice` (`assetCode`, `date`, `price`) VALUES ('VET', '2021-09-06', 78.89);

insert into portfolio (username, assetCode, amount)
values ('TestUser101', 'BTC', 7566.00);
insert into portfolio (username, assetCode, amount)
values ('TestUser102', 'ETH', 5975.00);
insert into portfolio (username, assetCode, amount)
values ('TestUser104', 'BTC', 3.00);
insert into portfolio (username, assetCode, amount)
values ('TestUser105', 'DOGE', 7566.00);
insert into portfolio (username, assetCode, amount)
values ('TestUser105', 'ETH', 5000.00);

insert into portfoliohistory (userName, date, totalValue)
VALUES ('TestUser201', '2021-09-04', 254159.50);
insert into portfoliohistory (userName, date, totalValue)
VALUES ('TestUser202', '2021-09-04', 254159.50);
insert into portfoliohistory (userName, date, totalValue)
VALUES ('TestUser202', '2021-09-03', 250);
insert into portfoliohistory (userName, date, totalValue)
VALUES ('TestUser202', '2021-09-02', 1);
insert into portfoliohistory (userName, date, totalValue)
VALUES ('TestUser105', '2021-09-04', 100);
insert into portfoliohistory (userName, date, totalValue)
VALUES ('TestUser105', '2021-09-05', 250.25);
insert into portfoliohistory (userName, date, totalValue)
VALUES ('TestUser105', '2021-09-06', 500.75);

INSERT INTO `transaction` (`id`, `assetCode`, `ibanSell`, `ibanBuy`, `amount`, `sellingPrice`, `transactionCost`, `date`) VALUES (1,'BTC','NL24DIGO1111111111','NL24DIGO2222222222',1,1000,0.01,'2020-01-01');
INSERT INTO `transaction` (`id`, `assetCode`, `ibanSell`, `ibanBuy`, `amount`, `sellingPrice`, `transactionCost`, `date`) VALUES (2,'ETH','NL24DIGO2222222222','NL24DIGO3333333333',2,1000,0.01,'2020-02-01');
INSERT INTO `transaction` (`id`, `assetCode`, `ibanSell`, `ibanBuy`, `amount`, `sellingPrice`, `transactionCost`, `date`) VALUES (3,'ADA','NL24DIGO2222222222','NL24DIGO4444444444',3,1500,0.02,'2020-03-01');

insert into customer (username, password, firstName, prefix, lastName, dateOfBirth, bsn, houseNumber, streetName,
                      zipCode, city, emailAddress, salt, status, iban)
values ('TestUser101','TestPassword', 'Tester', 'van', 'Tester', '1900-01-01', '753654852', '1', 'TestStraat', '1111AA',
        'TestCity', 'tester101@gmail.com', 'testzoutje',true, 'Nl123456789' );
insert into customer (username, password, firstName, prefix, lastName, dateOfBirth, bsn, houseNumber, streetName,
                      zipCode, city, emailAddress, salt, status, iban)
values ('TestUser102','TestPassword', 'Tester', 'van', 'Tester', '1900-01-01', '753654852', '1', 'TestStraat', '1111AA',
        'TestCity', 'tester102@gmail.com', 'testzoutje',true, 'Nl123456789' );
insert into customer (username, password, firstName, prefix, lastName, dateOfBirth, bsn, houseNumber, streetName,
                      zipCode, city, emailAddress, salt, status, iban)
values ('TestUser104','TestPassword', 'Tester', 'van', 'Tester', '1900-01-01', '753654852', '1', 'TestStraat', '1111AA',
        'TestCity', 'tester104@gmail.com', 'testzoutje',true, 'Nl123456789' );
insert into customer (username, password, firstName, prefix, lastName, dateOfBirth, bsn, houseNumber, streetName,
                      zipCode, city, emailAddress, salt, status, iban)
values ('TestUser105','TestPassword', 'Tester', 'van', 'Tester', '1900-01-01', '753654852', '1', 'TestStraat', '1111AA',
        'TestCity', 'tester1055@gmail.com', 'testzoutje',true, 'Nl123456789' );