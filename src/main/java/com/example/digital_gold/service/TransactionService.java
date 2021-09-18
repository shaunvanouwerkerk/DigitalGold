package com.example.digital_gold.service;

import com.example.digital_gold.domain.*;
import com.example.digital_gold.helper.TransactionFeeHelper;
import com.example.digital_gold.repository.RootRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

/**
 * @author David Truijens
 */

//TODO: tonen we in de front end het percentage transactiekosten?

@Service
public class TransactionService {

    private RootRepository rootRepository;

    private final Logger logger = LoggerFactory.getLogger(TransactionService.class);

    @Autowired
    public TransactionService(RootRepository rootRepository) {
        this.rootRepository = rootRepository;
    }

    public Transaction processTransaction(Transaction transaction){
        if(checkAccountBalanceBuyer(transaction)) {
            logger.info("Buyer has sufficient account balance");
       } else {
            logger.info("Buyer has insufficient account balance!");
           return null;
        }
        if(checkAssetAmount(transaction)) {
            logger.info("Seller has sufficient asset amount");
       } else {
           logger.info("Seller has insufficient asset amount!");
           return null;
        }
        logger.info("Transaction validation successful.");
        adjustAccountBalances(transaction);
        adjustPortfolioAmount(transaction);
        return rootRepository.saveTransaction(transaction);
    }

    public Boolean checkAccountBalanceBuyer(Transaction transaction) {
        double transactionValue = transaction.getAssetPrice() * transaction.getAssetAmount();
        double valueBuyer = TransactionFeeHelper.splitTransactionFee(transaction).getFeeBuyer() + transactionValue;
        double bankBalance = rootRepository.getBalanceByIban(transaction.getIbanBuy());
        return (bankBalance >= valueBuyer);
    }

    public Boolean checkAssetAmount(Transaction transaction) {
        String usernameSeller = rootRepository.findUsernameByIban(transaction.getIbanSell());
        String assetCode = transaction.getAssetCode();
        double portfolioAssetAmount = rootRepository.getPortfolioAssetByUsernameAssetCode(usernameSeller,assetCode);
        return (portfolioAssetAmount >= transaction.getAssetAmount());
    }

    public void adjustAccountBalances(Transaction transaction){
        double transactionValue = transaction.getAssetPrice() * transaction.getAssetAmount();
        double valueSeller = transactionValue - TransactionFeeHelper.splitTransactionFee(transaction).getFeeSeller();
        double valueBuyer = TransactionFeeHelper.splitTransactionFee(transaction).getFeeBuyer() + transactionValue;
        double transactionCosts = transaction.getAssetAmount() * transaction.getAssetPrice() * transaction.getTransactionFee();
        double newBalanceSeller = Math.round((rootRepository.getBalanceByIban(transaction.getIbanSell()) + valueSeller) * 100.0) / 100.0;
        double newBalanceBuyer = Math.round((rootRepository.getBalanceByIban(transaction.getIbanBuy()) - valueBuyer) * 100.0) / 100.0;
        double newBalanceBank = Math.round((rootRepository.getBalanceByIban(transaction.getIbanBuy()) + transactionCosts) * 100.0) / 100.0;
        BankAccount bankAccountSeller = new BankAccount(transaction.getIbanSell(),newBalanceSeller);
        BankAccount bankAccountBuyer = new BankAccount(transaction.getIbanBuy(), newBalanceBuyer);
        BankAccount bankAccountBank = new BankAccount(TransactionFeeHelper.getIbanBank(),newBalanceBank);
        rootRepository.updateBalance(bankAccountBuyer);
        rootRepository.updateBalance(bankAccountSeller);
        rootRepository.updateBalance(bankAccountBank);
        logger.info("AccountBalances updated.");
    }

    public void adjustPortfolioAmount(Transaction transaction) {
        Asset asset = rootRepository.findAssetByAssetCode(transaction.getAssetCode());
        double assetAmount = transaction.getAssetAmount();
        String assetCode = transaction.getAssetCode();
        String usernameSeller = rootRepository.findUsernameByIban(transaction.getIbanSell());
        String usernameBuyer = rootRepository.findUsernameByIban(transaction.getIbanBuy());
        double newAssetAmountSeller = rootRepository.getPortfolioAssetByUsernameAssetCode(usernameSeller,assetCode) - assetAmount;
        Customer seller = rootRepository.findAndReturnCustomerByUsername(usernameSeller);
        Map<Asset, Double> sellerPfAsset = new HashMap<Asset, Double>();
        sellerPfAsset.put(asset,newAssetAmountSeller);
        Portfolio portfolioSeller = new Portfolio(seller,sellerPfAsset);
        double newAssetAmountBuyer = rootRepository.getPortfolioAssetByUsernameAssetCode(usernameBuyer,assetCode) + assetAmount;
        Customer buyer = rootRepository.findAndReturnCustomerByUsername(usernameBuyer);
        Map<Asset, Double> buyerPfAsset = new HashMap<Asset, Double>();
        buyerPfAsset.put(asset,newAssetAmountBuyer);
        Portfolio portfolioBuyer = new Portfolio(buyer,buyerPfAsset);
        rootRepository.saveAssetChangesInPortfolio(portfolioBuyer);
        rootRepository.saveAssetChangesInPortfolio(portfolioSeller);
        logger.info("Portfolio assets updated.");
    }

    public RootRepository getRootRepository() {
        return rootRepository;
    }
}
