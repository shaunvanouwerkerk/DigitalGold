package com.example.digital_gold.controller;

import com.example.digital_gold.domain.Asset;
import com.example.digital_gold.domain.AssetPrice;
import com.example.digital_gold.domain.CryptoApiAssetPrice;
import com.example.digital_gold.repository.JdbcAssetDao;
import com.example.digital_gold.repository.RootRepository;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class ApiLauncher {


    public static void main(String[] args) throws IOException, InterruptedException {

        final String CRYPTO_API_URL =
                "https://api.coingecko.com/api/v3/coins/markets?vs_currency=eur&order=market_cap_desc&per_page=30&page=1&sparkline=false";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("accept", "application/json")
                .uri(URI.create(CRYPTO_API_URL))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        //CryptoApi crypto = new CryptoApi();
        //logger.info(response.body());
        System.out.println(response.body());

        // parse JSON into objects --> pom xml jackson toevoegen dependency
        // parse JSON into AssetPrice
        ObjectMapper mapper = new ObjectMapper();
        List<CryptoApiAssetPrice> prices = mapper.readValue(response.body(), new TypeReference<>(){});
        //List<CryptoApiAssetPrice> twentyPrices = new ArrayList<>();
        //List<Asset> assets = rootrepository.findAllAssets();

        for (CryptoApiAssetPrice price : prices) {
            /*for (Asset asset : assets) {
                if (price.getSymbol().equals(asset.getAssetCode())) {
                    twentyPrices.add(price);
                }
            }*/
            System.out.println(price);
        }
        /*for (CryptoApiAssetPrice price : twentyPrices) {
            System.out.println(price);
        }*/
    }
}
