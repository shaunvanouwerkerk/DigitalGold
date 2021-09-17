package com.example.digital_gold.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

/**
* @author Fiona Gray
* */

@JsonIgnoreProperties(ignoreUnknown = true)
public class CryptoApiAssetPrice {

    @JsonProperty("symbol")
    private String symbol;

    @JsonProperty("current_price")
    private double currentPrice;

    @JsonProperty("name")
    private String name;

    @JsonProperty("image")
    private String image;

    public CryptoApiAssetPrice(String symbol, double currentPrice, String name, String image) {
        this.symbol = symbol;
        this.currentPrice = currentPrice;
        this.name = name;
        this.image = image;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CryptoApiAssetPrice that = (CryptoApiAssetPrice) o;
        return Double.compare(that.currentPrice, currentPrice) == 0 && Objects.equals(symbol, that.symbol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, currentPrice);
    }
}
