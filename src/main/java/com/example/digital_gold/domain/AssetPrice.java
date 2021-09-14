package com.example.digital_gold.domain;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author Fiona Gray
 * */

public class AssetPrice {

    private Asset asset;
    private double price;
    private LocalDateTime dateTime;

    // todo uitzoeken of deze constructor private/public moet zijn
    public AssetPrice(Asset asset, double price, LocalDateTime dateTime) {
        this.asset = asset;
        this.price = price;
        this.dateTime = dateTime;
    }

    public AssetPrice(double price, LocalDateTime dateTime) {
        this(null, price, dateTime);
    }

    public AssetPrice() {
    }

    public Asset getAsset() {
        return asset;
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

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "AssetPrice{" +
                "asset=" + asset +
                ", price=" + price +
                ", dateTime=" + dateTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AssetPrice that = (AssetPrice) o;
        return Double.compare(that.price, price) == 0 && Objects.equals(asset, that.asset) && Objects.equals(dateTime, that.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(asset, price, dateTime);
    }
}
