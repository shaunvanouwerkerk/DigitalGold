package com.example.digital_gold.domain;

public class Asset {
    private String assetCode;
    private String assetName;
    private String description;

    public Asset() {
    }

    public Asset(String assetCode, String assetName, String description) {
        this.assetCode = assetCode;
        this.assetName = assetName;
        this.description = description;
    }

    public String getAssetCode() {
        return assetCode;
    }

    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Asset{" +
                "assetCode='" + assetCode + '\'' +
                ", assetName='" + assetName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}
