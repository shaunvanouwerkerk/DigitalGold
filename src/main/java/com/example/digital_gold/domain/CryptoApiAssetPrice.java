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
    private String assetName;

    @JsonProperty("image")
    private String image;

    public CryptoApiAssetPrice(String symbol, double currentPrice, String assetName) {
        this.symbol = symbol;
        this.currentPrice = currentPrice;
        this.assetName = assetName;
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

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CryptoApiAssetPrice that = (CryptoApiAssetPrice) o;
        return Double.compare(that.currentPrice, currentPrice) == 0 && Objects.equals(symbol, that.symbol) && Objects.equals(assetName, that.assetName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, currentPrice, assetName);
    }

    @Override
    public String toString() {
        return "CryptoApiAssetPrice{" +
                "symbol='" + symbol + '\'' +
                ", currentPrice=" + currentPrice +
                ", assetName='" + assetName + '\'' +
                '}';
    }
}
