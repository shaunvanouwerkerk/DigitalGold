package com.example.digital_gold.domain;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDateTime;
import java.util.Objects;

/**
@author David Truijens
 */

public class Transaction {

    private int transactionId;
    private LocalDateTime transactionDate;
    private String assetCode;
    private double assetAmount;
    private double assetPrice;
    private double transactionFee; //kosten in euro/dollar of percentage??
    private String ibanSell;
    private String ibanBuy;

    private final Logger logger = LoggerFactory.getLogger(Transaction.class);

    public Transaction() {
        this.transactionId = 0;
        transactionDate = LocalDateTime.now();
        logger.info("New empty transaction with timestamp: " + this.transactionDate);
    }

    public Transaction(LocalDateTime transactionDate, String assetCode, double assetAmount, double assetPrice,
                       double transactionFee, String ibanSell, String ibanBuy) {
        this(0,transactionDate,assetCode,assetAmount,assetPrice,transactionFee,ibanSell,ibanBuy);
        logger.info("New transaction: " + this);
    }

    public Transaction(int transactionId, LocalDateTime transactionDate, String assetCode, double assetAmount, double assetPrice,
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

    public LocalDateTime getTransactionDate() {
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
