package com.example.digital_gold.service;

import com.example.digital_gold.domain.BankAccount;
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
        //TODO: amount van verkoper checken
        // balances bijwerken, asset amounts bijwerken.
        //checken of de verkoper genoeg assets heeft:


        return rootRepository.saveTransaction(transaction);
    }




    public Boolean checkAccountBalance(double value, String iban) {
        //TODO: Methode nodig die Bankaccount via IBAN ophaalt. Constructor call vervangen!!
        BankAccount bankAccount = new BankAccount();//rootRepository.getBankAccountByIban(iban);
        double accountBalance = bankAccount.getBalance();
        return (accountBalance >= value);
    }

    public Boolean checkAssetAmount(double amount, String userName, String assetCode) {
        //TODO: Portfolio asset amount van verkoper vergelijken met transactie asset aantal.
        // uit transactie iban van verkoper halen en via Customer checken welke username om
        // in de juiste portfolio te kijken bij de juiste asset naar de beschikbare hoeveelheid

        return false;
    }

    public void adjustAccountBalances(Transaction transaction, TransactionFeeHelper transactionFeeHelper){
        //TODO: saldo van koper en verkoper aanpassen
    }

    public void adjustPortfolioAmount(Transaction transaction) {
        //TODO: aantal van de asset bijwerken in portfolio van koper en verkoper.
    }

    public RootRepository getRootRepository() {
        return rootRepository;
    }
}
