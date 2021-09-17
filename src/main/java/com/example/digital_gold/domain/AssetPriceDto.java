package com.example.digital_gold.domain;

import java.util.Objects;

public class AssetPriceDto {

    String symbol;
    String assetName;
    double currentPrice;

    public AssetPriceDto(String symbol, String assetName, double currentPrice) {
        this.symbol = symbol;
        this.assetName = assetName;
        this.currentPrice = currentPrice;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AssetPriceDto that = (AssetPriceDto) o;
        return Double.compare(that.currentPrice, currentPrice) == 0 && Objects.equals(symbol, that.symbol) && Objects.equals(assetName, that.assetName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, assetName, currentPrice);
    }

    @Override
    public String toString() {
        return "AssetPriceDTO{" +
                "symbol='" + symbol + '\'' +
                ", name='" + assetName + '\'' +
                ", currentPrice=" + currentPrice +
                '}';
    }
}
