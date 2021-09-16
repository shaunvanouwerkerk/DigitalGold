package com.example.digital_gold.service;

import java.util.Objects;

/*
@Author Jany Gaal
*/

public class PortfolioAssetOverview {

    private String assetName;
    private String assetCode;
    private double currentPrice;
    private double amountOfAsset;
    private double assetTotalValue;

    public PortfolioAssetOverview(String assetName, String assetCode, double currentPrice, double amountOfAsset, double assetTotalValue) {
        this.assetName = assetName;
        this.assetCode = assetCode;
        this.currentPrice = currentPrice;
        this.amountOfAsset = amountOfAsset;
        this.assetTotalValue = assetTotalValue;
    }

    public PortfolioAssetOverview() {
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public String getAssetCode() {
        return assetCode;
    }

    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public double getAmountOfAsset() {
        return amountOfAsset;
    }

    public void setAmountOfAsset(double amountOfAsset) {
        this.amountOfAsset = amountOfAsset;
    }

    public double getAssetTotalValue() {
        return assetTotalValue;
    }

    public void setAssetTotalValue(double assetTotalValue) {
        this.assetTotalValue = assetTotalValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PortfolioAssetOverview)) return false;
        PortfolioAssetOverview that = (PortfolioAssetOverview) o;
        return Double.compare(that.getCurrentPrice(), getCurrentPrice()) == 0 && Double.compare(that.getAmountOfAsset(), getAmountOfAsset()) == 0 && Double.compare(that.getAssetTotalValue(), getAssetTotalValue()) == 0 && Objects.equals(getAssetName(), that.getAssetName()) && Objects.equals(getAssetCode(), that.getAssetCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAssetName(), getAssetCode(), getCurrentPrice(), getAmountOfAsset(), getAssetTotalValue());
    }

    @Override
    public String toString() {
        return "PortfolioAssetOverview{" +
                "assetName='" + assetName + '\'' +
                ", assetCode='" + assetCode + '\'' +
                ", currentPrice=" + currentPrice +
                ", amountOfAsset=" + amountOfAsset +
                ", assetTotalValue=" + assetTotalValue +
                '}';
    }
}