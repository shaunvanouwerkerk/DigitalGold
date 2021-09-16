package com.example.digital_gold.helper;


import com.example.digital_gold.domain.Transaction;

/**
 * @author David Truijens
 *
 * Helper voor het verdelen van de transactiekosten tussen koper en verkoper
 * Logica: Als IBAN_BANK == ibanBuy dan seller 100% fee
 *          Als IBAN_BANK == ibanSell dan buyer 100% fee
 *          anders seller 50% fee en buyer 50% fee
 *
 * Hier wordt ook de IBAN van de bank vastgelegd, de transactionfee
 * en de verdeling van de transactiekosten tussen klant transacties.
 * Onderhoudbaar door de Admin
 */

public class TransactionFeeHelper {

    private static String ibanBank = "NL00DIGO0000000001";
    private static double transactionFee = 0.01;
    private static double shareBuyer = 0.5;
    private static double shareSeller =0.5;
    private double feeBuyer;
    private double feeSeller;


    public TransactionFeeHelper(double feeBuyer, double feeSeller) {
        this.feeBuyer = feeBuyer;
        this.feeSeller = feeSeller;
    }

    public static TransactionFeeHelper splitTransactionFee(Transaction transaction) {
        double feeBuyer = 0, feeSeller = 0;
        double transactionValue = transaction.getAssetPrice() * transaction.getAssetAmount();
        double transactionCosts = transactionValue * transaction.getTransactionFee();
        if(transaction.getIbanSell().equals(ibanBank)) {
            feeBuyer = transactionCosts;
        } else if (transaction.getIbanBuy().equals(ibanBank)) {
            feeSeller = transactionCosts;
        } else {
            feeBuyer = transactionCosts * shareBuyer;
            feeSeller = transactionCosts * shareSeller;
        }
        return new TransactionFeeHelper(feeBuyer,feeSeller);
    }

    public static String getIbanBank() {
        return ibanBank;
    }

    public void setIbanBank(String ibanBank) {
        TransactionFeeHelper.ibanBank = ibanBank;
    }

    public static double getTransactionFee() { return transactionFee; }

    public static void setTransactionFee(double transactionFee) { TransactionFeeHelper.transactionFee = transactionFee; }

    public double getShareBuyer() {
        return shareBuyer;
    }

    public void setShareBuyer(double shareBuyer) {
        TransactionFeeHelper.shareBuyer = shareBuyer;
    }

    public double getShareSeller() {
        return shareSeller;
    }

    public void setShareSeller(double shareSeller) {
        TransactionFeeHelper.shareSeller = shareSeller;
    }

    public double getFeeBuyer() {
        return feeBuyer;
    }

    public double getFeeSeller() {
        return feeSeller;
    }
}



