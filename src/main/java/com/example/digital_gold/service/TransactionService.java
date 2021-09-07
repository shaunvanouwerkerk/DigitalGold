package com.example.digital_gold.service;

import com.example.digital_gold.domain.BankAccount;
import com.example.digital_gold.domain.Transaction;
import com.example.digital_gold.helper.TransactionFeeHelper;
import com.example.digital_gold.repository.JdbcPortfolioDao;
import com.example.digital_gold.repository.PortfolioDatabase;
import com.example.digital_gold.repository.RootRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author David Truijens
 */

//TODO: check of AuthenticatorService nodig is voor transactie.
// Berekenen we transactiekosten in front-end al? Lijkt logisch, of tonen we alleen het percentage transactiekosten?

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
        //TODO: asset amounts bijwerken.
        logger.info("Transaction validation successful.");
        adjustAccountBalances(transaction);
        //TODO: adjustPortfolioAmount(transaction);
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
        double portfolioAssetAmount = 100.0; //TODO: rootRepository.getPortfolioAssetByUsernameAssetCode(usernameSeller,assetCode);
        return (portfolioAssetAmount >= transaction.getAssetAmount());
    }

    public void adjustAccountBalances(Transaction transaction){
        double transactionValue = transaction.getAssetPrice() * transaction.getAssetAmount();
        double valueSeller = transactionValue - TransactionFeeHelper.splitTransactionFee(transaction).getFeeSeller();
        double valueBuyer = TransactionFeeHelper.splitTransactionFee(transaction).getFeeBuyer() + transactionValue;
        double transactionCosts = transaction.getAssetAmount() * transaction.getAssetPrice() * transaction.getTransactionFee();
        double newBalanceSeller = rootRepository.getBalanceByIban(transaction.getIbanSell()) + valueSeller;
        double newBalanceBuyer = rootRepository.getBalanceByIban(transaction.getIbanBuy()) - valueBuyer;
        double newBalanceBank = rootRepository.getBalanceByIban(transaction.getIbanBuy()) + transactionCosts;
        BankAccount bankAccountSeller = new BankAccount(transaction.getIbanSell(),newBalanceSeller);
        BankAccount bankAccountBuyer = new BankAccount(transaction.getIbanBuy(), newBalanceBuyer);
        BankAccount bankAccountBank = new BankAccount(TransactionFeeHelper.getIbanBank(),newBalanceBank);
        rootRepository.updateBalance(bankAccountBuyer);
        rootRepository.updateBalance(bankAccountSeller);
        rootRepository.updateBalance(bankAccountBank);
        logger.info("AccountBalances updated.");
    }

    public void adjustPortfolioAmount(Transaction transaction) {
        //TODO: aantal van de asset bijwerken in portfolio van koper en verkoper.
        double assetAmount = transaction.getAssetAmount();
        String assetCode = transaction.getAssetCode();
        String usernameSeller = rootRepository.findUsernameByIban(transaction.getIbanSell());
        String usernameBuyer = rootRepository.findUsernameByIban(transaction.getIbanBuy());
        double newAssetAmountSeller = 100.0; //TODO: rootRepository.getPortfolioAssetByUsernameAssetCode(usernameSeller) - assetAmount;
        double newAssetAmountBuyer = 100.0; //TODO: rootRepository.getPortfolioAssetByUsernameAssetCode(usernameBuyer) + assetAmount;
        PortfolioDatabase pfDbSeller = new PortfolioDatabase(usernameSeller,assetCode,newAssetAmountSeller);
        PortfolioDatabase pfDbBuyer = new PortfolioDatabase(usernameBuyer,assetCode,newAssetAmountBuyer);

        logger.info("Portfolio assets updated.");
    }

    public RootRepository getRootRepository() {
        return rootRepository;
    }
}
