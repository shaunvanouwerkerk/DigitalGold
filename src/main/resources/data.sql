insert into customer (username, password, firstName, prefix, lastName, dateOfBirth, bsn, houseNumber, streetName,
                            zipCode, city, emailAddress, salt, status, iban)
values ('TestUser01','TestPassword', 'Tester', 'van', 'Tester', '1900-01-01', '753654852', '1', 'TestStraat', '1111AA',
        'TestCity', 'tester1@gmail.com', 'testzoutje',true, 'Nl 123456789' );

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