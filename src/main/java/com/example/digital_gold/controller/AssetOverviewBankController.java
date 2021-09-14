package com.example.digital_gold.controller;
import com.example.digital_gold.domain.CryptoApiAssetPrice;
import com.example.digital_gold.service.AssetOverviewBankService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

/**
 * @author Fiona Gray
* */

@RestController
public class AssetOverviewBankController {

    private AssetOverviewBankService assetOverviewBankService;

    private final Logger logger = LoggerFactory.getLogger(AssetOverviewBankController.class);

    @Autowired
    public AssetOverviewBankController(AssetOverviewBankService assetOverviewBankService) {
        this.assetOverviewBankService = assetOverviewBankService;
        logger.info("New AssetOverviewBankController");
    }

    private static final String CRYPTO_API_URL =
            "https://api.coingecko.com/api/v3/coins/markets?vs_currency=eur&order=market_cap_desc&per_page=30&page=1&sparkline=false";

    @GetMapping ("/assetoverviewbank")
    public List<CryptoApiAssetPrice> getAssetOverviewBank() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("accept", "application/json")
                .uri(URI.create(CRYPTO_API_URL))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        logger.info("CryptoAPI HTTPResponse created");

        // parse JSON into objects
        ObjectMapper mapper = new ObjectMapper();
        List<CryptoApiAssetPrice> prices = mapper.readValue(response.body(), new TypeReference<>(){});
        List<CryptoApiAssetPrice> twentyPrices = assetOverviewBankService.getAndSaveTwentyPrices(prices);
        return twentyPrices;
    }

    // via randomgenerator
  /*  // retrieves list of all available assets + current prices from database via randomgenerator
    @GetMapping ("/assetoverviewbank")
    public ResponseEntity<?> getAssetOverviewBank() {
        try {
            return new ResponseEntity(assetOverviewBankService.getAssetOverviewBank(LocalDate.now()), HttpStatus.OK);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }*/
}
