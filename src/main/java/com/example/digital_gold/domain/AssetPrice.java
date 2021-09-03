package com.example.digital_gold.domain;

import java.time.LocalDate;

/**
 * @author Fiona Gray
 * */

public class AssetPrice {

    private Asset asset;
    private double price;
    private LocalDate date;

    // todo uitzoeken of deze constructor private/public moet zijn
    public AssetPrice(Asset asset, double price, LocalDate date) {
        this.asset = asset;
        this.price = price;
        this.date = date;
    }

    public AssetPrice(double price, LocalDate date) {
        this(null, price, date);
    }

    public Asset getAsset() {
        return asset;
    }

    public AssetPrice() {
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "AssetPrice{" +
                "asset=" + asset +
                ", price=" + price +
                ", date=" + date +
                '}';
    }
}
