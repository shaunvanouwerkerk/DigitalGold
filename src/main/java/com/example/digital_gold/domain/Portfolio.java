package com.example.digital_gold.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/*
@Author Jany Gaal
*/

public class Portfolio {

    private Customer customer;
    private Map<Asset, Double> assetList = new HashMap<>();

    public Portfolio(Customer customer, Map<Asset, Double> assetList) {
        this.customer = customer;
        this.assetList = assetList;
    }

    public Portfolio() {
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Map<Asset, Double> getAssetList() {
        return assetList;
    }

    public void setAssetList(Map<Asset, Double> assetList) {
        this.assetList = assetList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Portfolio)) return false;
        Portfolio portfolio = (Portfolio) o;
        return Objects.equals(getCustomer(), portfolio.getCustomer()) && Objects.equals(getAssetList(), portfolio.getAssetList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCustomer(), getAssetList());
    }

    @Override
    public String toString() {
        return "Portfolio{" +
                "username=" + customer +
                ", assetList=" + assetList +
                '}';
    }
}
