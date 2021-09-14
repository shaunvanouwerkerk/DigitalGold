package com.example.digital_gold.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CryptoApiAssetPrice {

    @JsonProperty("symbol")
    private String symbol;

    @JsonProperty("current_price")
    private long currentPrice;

    public CryptoApiAssetPrice(String symbol, long currentPrice) {
        this.symbol = symbol;
        this.currentPrice = currentPrice;
    }

    public CryptoApiAssetPrice() {
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public long getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(long currentPrice) {
        this.currentPrice = currentPrice;
    }

    @Override
    public String toString() {
        return "CryptoApiAssetPrice{" +
                "symbol='" + symbol + '\'' +
                ", currentPrice=" + currentPrice +
                '}';
    }


}
