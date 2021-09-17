package com.example.digital_gold.service;

import com.example.digital_gold.domain.Asset;
import com.example.digital_gold.domain.AssetPrice;
import com.example.digital_gold.domain.AssetPriceDto;
import com.example.digital_gold.domain.CryptoApiAssetPrice;
import com.example.digital_gold.repository.RootRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
* @author Fiona Gray
* */

@Service
public class AssetOverviewBankService {

    private RootRepository rootRepository;
    private final Logger logger = LoggerFactory.getLogger(AssetOverviewBankService.class);
    private static final String CRYPTO_API_URL =
            "https://api.coingecko.com/api/v3/coins/markets?vs_currency=eur&order=market_cap_desc&per_page=30&page=1&sparkline=false";

    @Autowired
    public AssetOverviewBankService(RootRepository rootRepository) {
        this.rootRepository = rootRepository;
        logger.info("New AssetOverviewBankService");
    }

    public List<AssetPriceDto> getAssetPricesFromDatabase() {
        List<Asset> assets = rootRepository.findAllAssets();
        List<AssetPriceDto> assetPriceDtoList = new ArrayList<>();
        for (Asset asset : assets) {
            AssetPrice assetPrice = rootRepository.findAssetPriceByAssetCode(asset.getAssetCode());
            AssetPriceDto assetPriceDto = new AssetPriceDto(asset.getAssetCode(), asset.getAssetName(), assetPrice.getPrice());
            assetPriceDtoList.add(assetPriceDto);
        }
        return assetPriceDtoList;
    }

    public void getAndSaveCryptoApiPrices() throws IOException, InterruptedException {
        HttpResponse<String> response = createHttpResponse();
        List<CryptoApiAssetPrice> thirtyPrices = parseResponseIntoObjects(response);
        compareAndSaveTwentyPrices(thirtyPrices);
    }

    public HttpResponse<String> createHttpResponse() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("accept", "application/json")
                .uri(URI.create(CRYPTO_API_URL))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        logger.info("CryptoAPI HTTPResponse created");
        return response;
    }

    public List<CryptoApiAssetPrice> parseResponseIntoObjects(HttpResponse<String> response) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        List<CryptoApiAssetPrice> prices = mapper.readValue(response.body(), new TypeReference<>(){});
        return prices;
    }

    private void compareAndSaveTwentyPrices(List<CryptoApiAssetPrice> thirtyPrices) {
        List<Asset> assets = rootRepository.findAllAssets();
        for(Asset asset : assets) {
            for (CryptoApiAssetPrice price : thirtyPrices) {
                if (price.getSymbol().toUpperCase(Locale.ROOT).equals(asset.getAssetCode())) {
                    roundAndSaveAssetPrice(asset, price);
                }
            }
        }
    }

    public void roundAndSaveAssetPrice(Asset asset, CryptoApiAssetPrice price) {
        double roundedAssetPrice = roundDouble(price.getCurrentPrice());
        AssetPrice assetPrice = new AssetPrice(asset, roundedAssetPrice, LocalDateTime.now());
        rootRepository.saveAssetPrice(assetPrice);
    }

    public double roundDouble(double price) {
        return Math.round(price * 1000.00) / 1000.00;
    }
}
