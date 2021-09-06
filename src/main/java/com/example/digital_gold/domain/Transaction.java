package com.example.digital_gold.domain;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

/**
@author David Truijens
 */

public class Transaction {

    private int transactionId;
    private LocalDate transactionDate;
    private String assetCode;
    private double assetAmount;
    private double assetPrice;
    private double transactionFee; //dit is het default percentage, in beheer van Admin
    private String ibanSell;
    private String ibanBuy;

    private final Logger logger = LoggerFactory.getLogger(Transaction.class);

    public Transaction() {
        this.transactionId = 0;
        transactionDate = LocalDate.now();
        logger.info("New empty transaction with timestamp: " + this.transactionDate);
    }

    public Transaction(LocalDate transactionDate, String assetCode, double assetAmount, double assetPrice,
                       double transactionFee, String ibanSell, String ibanBuy) {
        this(0,transactionDate,assetCode,assetAmount,assetPrice,transactionFee,ibanSell,ibanBuy);
        logger.info("New transaction: " + this);
    }

    public Transaction(int transactionId, LocalDate transactionDate, String assetCode, double assetAmount, double assetPrice,
                       double transactionFee, String ibanSell, String ibanBuy) {
        this.transactionId = transactionId;
        this.transactionDate = transactionDate;
        this.assetCode = assetCode;
        this.assetAmount = assetAmount;
        this.assetPrice = assetPrice;
        this.transactionFee = transactionFee;
        this.ibanSell = ibanSell;
        this.ibanBuy = ibanBuy;
        logger.info("New transaction: " + this);
    }


    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public String getAssetCode() {
        return assetCode;
    }

    public double getAssetAmount() {
        return assetAmount;
    }

    public double getAssetPrice() {
        return assetPrice;
    }

    public double getTransactionFee() {
        return transactionFee;
    }

    public String getIbanSell() {
        return ibanSell;
    }

    public String getIbanBuy() {
        return ibanBuy;
    }

    public void setTransactionFee(double transactionFee) {
        this.transactionFee = transactionFee;
    }


    //TODO: Setters voor testen van TransactionContoller.

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode;
    }

    public void setAssetAmount(double assetAmount) {
        this.assetAmount = assetAmount;
    }

    public void setAssetPrice(double assetPrice) {
        this.assetPrice = assetPrice;
    }

    public void setIbanSell(String ibanSell) {
        this.ibanSell = ibanSell;
    }

    public void setIbanBuy(String ibanBuy) {
        this.ibanBuy = ibanBuy;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", transactionDate=" + transactionDate +
                ", assetCode='" + assetCode + '\'' +
                ", assetAmount=" + assetAmount +
                ", assetPrice=" + assetPrice +
                ", transactionFee=" + transactionFee +
                ", ibanSell='" + ibanSell + '\'' +
                ", ibanBuy='" + ibanBuy + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return transactionId == that.transactionId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionId);
    }
}
