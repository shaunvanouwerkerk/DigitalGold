package com.example.digital_gold.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
* @author Fiona Gray
* */

@JsonIgnoreProperties(ignoreUnknown = true)
public class CryptoApiAssetPrice {

    @JsonProperty("symbol")
    private String symbol;

    @JsonProperty("current_price")
    private double currentPrice;

    public CryptoApiAssetPrice(String symbol, double currentPrice) {
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

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
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
