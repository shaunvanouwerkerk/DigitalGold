package com.example.digital_gold.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CryptoApi {

    @JsonProperty("symbol")
    private String symbol;

    @JsonProperty("current_price")
    private long currentPrice;

    public CryptoApi(String symbol, long currentPrice) {
        this.symbol = symbol;
        this.currentPrice = currentPrice;
    }

    public CryptoApi() {
    }

    @Override
    public String toString() {
        return "CryptoApi{" +
                "symbol='" + symbol + '\'' +
                ", currentPrice=" + currentPrice +
                '}';
    }
}
