package com.example.digital_gold.domain;

import java.util.Objects;

/**
 * @author Fiona Gray
 * */

public class Asset {
    private String assetCode;
    private String assetName;
    private String description;

    public Asset(String assetCode, String assetName, String description) {
        this.assetCode = assetCode;
        this.assetName = assetName;
        this.description = description;
    }

    public Asset() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Asset asset = (Asset) o;
        return Objects.equals(assetCode, asset.assetCode) && Objects.equals(assetName, asset.assetName) && Objects.equals(description, asset.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(assetCode, assetName, description);
    }
}
