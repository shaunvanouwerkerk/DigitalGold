package com.example.digital_gold.service;

import com.example.digital_gold.domain.Transaction;
import com.example.digital_gold.helper.TransactionFeeHelper;
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
        double transactionValue = transaction.getAssetPrice() * transaction.getAssetAmount();
        double valueBuyer = TransactionFeeHelper.splitTransactionFee(transaction).getFeeBuyer() + transactionValue;
        double valueSeller = TransactionFeeHelper.splitTransactionFee(transaction).getFeeSeller();
        String usernameBuyer = rootRepository.findUsernameByIban(transaction.getIbanBuy());
        String usernameSeller = rootRepository.findUsernameByIban(transaction.getIbanSell());

        //checken of koper genoeg saldo heeft:
        if(checkAccountBalance(valueBuyer,transaction.getIbanBuy())) {
            logger.info("Buyer has sufficient account balance");
        } else {
            logger.info("Buyer has insufficient account balance!");
            return null;
        }

        //checken of verkoper genoeg saldo heeft:
        if(checkAccountBalance(valueSeller,transaction.getIbanSell())) {
            logger.info("Seller has sufficient account balance");
        } else {
            logger.info("Seller has insufficient account balance!");
            return null;
        }

        //checken of de verkoper genoeg assets heeft:
        if(checkAssetAmount(transaction.getAssetAmount(),usernameSeller,transaction.getAssetCode())) {
            logger.info("Seller has sufficient asset amount");
        } else {
            logger.info("Seller has insufficient asset amount!");
            return null;
        }

        //TODO: balances bijwerken, asset amounts bijwerken.
        logger.info("Transaction validation successful.");

        adjustAccountBalances(transaction,valueBuyer,valueSeller);


        return rootRepository.saveTransaction(transaction);
    }

    public Boolean checkAccountBalance(double value, String iban) {
        double bankBalance = rootRepository.getBalanceByIban(iban);
        return (bankBalance >= value);
    }

    public Boolean checkAssetAmount(double amount, String userName, String assetCode) {
        double portfolioAssetAmount = 100.0; //TODO: rootRepository.getPortfolioAssetAmountByUsernameAssetCode(userName,assetCode);
        return (portfolioAssetAmount >= amount);
    }

    public void adjustAccountBalances(Transaction transaction, double valueBuyer, double valueSeller){
        //TODO: saldo van koper en verkoper aanpassen
        logger.info("AccountBalances updated.");
    }

    public void adjustPortfolioAmount(Transaction transaction) {
        //TODO: aantal van de asset bijwerken in portfolio van koper en verkoper.
        logger.info("Portfolio assets updated.");
    }

    public RootRepository getRootRepository() {
        return rootRepository;
    }
}
