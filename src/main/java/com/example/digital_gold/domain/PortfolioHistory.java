package com.example.digital_gold.domain;

import java.time.LocalDate;
import java.util.Objects;

/*
@Author Jany Gaal
*/

public class PortfolioHistory implements Comparable<PortfolioHistory> {

    private Customer customer;
    private LocalDate date;
    private double totalValue;

    public PortfolioHistory(Customer customer, LocalDate date, double totalValue) {
        this.customer = customer;
        this.date = date;
        this.totalValue = totalValue;
    }

    public PortfolioHistory() {
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getTotalvalue() {
        return totalValue;
    }

    public void setTotalvalue(double totalvalue) {
        this.totalValue = totalvalue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PortfolioHistory)) return false;
        PortfolioHistory that = (PortfolioHistory) o;
        return Double.compare(that.getTotalvalue(), getTotalvalue()) == 0 && Objects.equals(getCustomer(), that.getCustomer()) && Objects.equals(getDate(), that.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCustomer(), getDate(), getTotalvalue());
    }

    @Override
    public String toString() {
        return "PortfolioHistory{" +
                "customer=" + customer +
                ", date=" + date +
                ", totalvalue=" + totalValue +
                '}';
    }

    @Override
    public int compareTo(PortfolioHistory o) {
        return this.date.compareTo(o.date);
    }
}


