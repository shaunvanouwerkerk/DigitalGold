package com.example.digital_gold.service;

import java.time.LocalDate;
import java.util.Objects;

/*
@Author Jany Gaal
*/

public class PortfolioValueOverview {

    private LocalDate date;
    private double portfolioValue;

    public PortfolioValueOverview(LocalDate date, double portfolioValue) {
        this.date = date;
        this.portfolioValue = portfolioValue;
    }

    public PortfolioValueOverview() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PortfolioValueOverview)) return false;
        PortfolioValueOverview that = (PortfolioValueOverview) o;
        return Double.compare(that.portfolioValue, portfolioValue) == 0 && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, portfolioValue);
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getPortfolioValue() {
        return portfolioValue;
    }

    public void setPortfolioValue(double portfolioValue) {
        this.portfolioValue = portfolioValue;
    }

    @Override
    public String toString() {
        return "PortfolioValueOverview{" +
                "date=" + date +
                ", portfolioValue=" + portfolioValue +
                '}';
    }
}