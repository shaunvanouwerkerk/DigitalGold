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
INSERT INTO `AssetPrice` (`assetCode`, `date`, `price`) VALUES ('BTC', '2021-09-04', 42273.5);

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



INSERT INTO `transaction` (`id`, `assetCode`, `ibanSell`, `ibanBuy`, `amount`, `sellingPrice`, `transactionCost`, `date`) VALUES (1,'BTC','NL24DIGO1111111111','NL24DIGO2222222222',1,1000,0.01,'2020-01-01');
INSERT INTO `transaction` (`id`, `assetCode`, `ibanSell`, `ibanBuy`, `amount`, `sellingPrice`, `transactionCost`, `date`) VALUES (2,'ETH','NL24DIGO2222222222','NL24DIGO3333333333',2,1000,0.01,'2020-02-01');
INSERT INTO `transaction` (`id`, `assetCode`, `ibanSell`, `ibanBuy`, `amount`, `sellingPrice`, `transactionCost`, `date`) VALUES (3,'ADA','NL24DIGO2222222222','NL24DIGO4444444444',3,1500,0.02,'2020-03-01');