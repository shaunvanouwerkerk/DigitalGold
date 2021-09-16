package com.example.digital_gold.service;

/**
 * @author Sandra Turina
 */

public class Order {

    // deze parameter krijgen we van de frontend request
    // username uit token
    private String username;
    private String assetCode;
    private double amountOfAsset;
    private String type;
    private double limit;

    public Order(String username, String assetCode, double amountOfAsset, String type, double limit) {
        this.username = username;
        this.assetCode = assetCode;
        this.amountOfAsset = amountOfAsset;
        this.type = type;
        this.limit = limit;
    }

    public Order(String username, String assetCode, double amountOfAsset, String type) {
        this.username = username;
        this.assetCode = assetCode;
        this.amountOfAsset = amountOfAsset;
        this.type = type;
        this.limit = 0; // klopt dit?
    }

    public Order() {
    }

    public String getUsername() {
        return username;
    }

    public String getAssetCode() {return assetCode; }

    public double getAmountOfAsset() {
        return amountOfAsset;
    }

    public String getType() {
        return type;
    }

    public double getLimit() {
        return limit;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode;
    }

    public void setAmountOfAsset(double amountOfAsset) {
        this.amountOfAsset = amountOfAsset;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setLimit(double limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "Order{" +
                "username='" + username + '\'' +
                ", assetCode='" + assetCode + '\'' +
                ", amountOfAsset=" + amountOfAsset +
                ", type='" + type + '\'' +
                ", limit=" + limit +
                '}';
    }
}
