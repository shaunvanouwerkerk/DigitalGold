package com.example.digital_gold.repository;

import java.util.Objects;

public class PortfolioDatabase {
    String username;
    String assetCode;
    double amount;

    public PortfolioDatabase() {
    }

    public String getUsername() {
        return username;
    }

    public String getAssetCode() {
        return assetCode;
    }

    public double getAmount() {
        return amount;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public PortfolioDatabase(String username, String assetCode, double amount) {
        this.username = username;
        this.assetCode = assetCode;
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PortfolioDatabase)) return false;
        PortfolioDatabase that = (PortfolioDatabase) o;
        return Double.compare(that.getAmount(), getAmount()) == 0 && Objects.equals(getUsername(), that.getUsername()) && Objects.equals(getAssetCode(), that.getAssetCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getAssetCode(), getAmount());
    }

    @Override
    public String toString() {
        return "PortfolioDatabase{" +
                "username='" + username + '\'' +
                ", assetCode='" + assetCode + '\'' +
                ", amount=" + amount +
                '}';
    }
}
