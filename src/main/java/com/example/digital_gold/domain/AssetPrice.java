package com.example.digital_gold.domain;

import java.time.LocalDate;
import java.util.Objects;

/**
 * @author Fiona Gray
 * */

// Todo: validation annotations
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AssetPrice that = (AssetPrice) o;
        return Double.compare(that.price, price) == 0 && Objects.equals(asset, that.asset) && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(asset, price, date);
    }
}
