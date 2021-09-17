package com.example.digital_gold.controller;

import com.example.digital_gold.domain.CryptoApiAssetPrice;
import com.example.digital_gold.service.AssetOverviewBankService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.ResourceUtils;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.when;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class AssetOverviewBankControllerTest {
    @Mock
    private AssetOverviewBankService assetOverviewBankService;

    private MockMvc mockMvc;
    private ObjectMapper mapper;

    @BeforeEach
    public void initRestAssuredMockStandAlone() {
        this.assetOverviewBankService = Mockito.mock(AssetOverviewBankService.class);
        RestAssuredMockMvc.standaloneSetup(new AssetOverviewBankController(assetOverviewBankService));
    }

    @Test
    public void assetOverviewBank_return200() {
        when()
                .get("/assetoverviewbank")
                .then()
                .statusCode(200);
    }

    @Test
    public void assetOverviewBank_returnContentTypeAppJson() {
        when()
                .get("/assetoverviewbank")
                .then()
                .contentType(MediaType.APPLICATION_JSON_VALUE);
    }

    public List<CryptoApiAssetPrice>  getJsonFileAndMapIntoObjects() throws IOException {
        File file = ResourceUtils.getFile("classpath:testData_30_cryptoApiAssets.json");
        System.out.println("File Found : " + file.exists());
        //Read File Content
        String content = new String(Files.readAllBytes(file.toPath()));

        ObjectMapper mapper = new ObjectMapper();
        List<CryptoApiAssetPrice> list = mapper.readValue(content, new TypeReference<>(){});
        return list;
    }

    @Test
    public void assetOverviewBank_returnResponseParsedIntoObjects() throws IOException {
        List<CryptoApiAssetPrice> expected = getJsonFileAndMapIntoObjects();
        CryptoApiAssetPrice btc = expected.get(0);
        CryptoApiAssetPrice eth = expected.get(1);
        CryptoApiAssetPrice ada = expected.get(2);

        List<CryptoApiAssetPrice> actual =
                when().get("/assetoverviewbank")
                        .then()
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .statusCode(200)
                        .extract()
                        .body().jsonPath()
                        .getList(".", CryptoApiAssetPrice.class);
        assertThat(actual.contains(ada.getSymbol()));
        assertThat(actual.contains(btc.getSymbol()));
        assertThat(actual.contains(eth.getSymbol()));
    }
   /* @Test
    public void assetOverviewBank_returnListOfTwentyApiPrices() throws IOException {
        List<CryptoApiAssetPrice> prices = getJsonFileAndMapIntoObjects();
        List<CryptoApiAssetPrice> twentyPrices = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            twentyPrices.add(prices.get(i));
        }
        CryptoApiAssetPrice btc = twentyPrices.get(0);

        //assertThat(Mockito.when(assetOverviewBankService.getAndSaveTwentyPrices(prices).containsAll(twentyPrices)));
        //assertThat(Mockito.when(assetOverviewBankService.getAndSaveTwentyPrices(prices).contains(btc.getSymbol())));
        //assertEquals(Mockito.when(assetOverviewBankService.getAndSaveTwentyPrices(prices).size()), twentyPrices.size());
        Mockito.when(assetOverviewBankService.getAndSaveTwentyPrices(prices));
        assertEquals(20, prices.size());
    }*/
}