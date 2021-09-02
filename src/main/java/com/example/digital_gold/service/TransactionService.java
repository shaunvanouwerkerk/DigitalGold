package com.example.digital_gold.service;


import com.example.digital_gold.domain.Transaction;
import com.example.digital_gold.repository.RootRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author David Truijens
 */

//TODO: check of AuthenticatorService nodig is voor transactie.
// Check of Helper nodig is voor berekenen/afhandelen transactiekosten.
// Berekenen we transactiekosten in front-end al? Lijkt logisch, of tonen we alleen het percentage transactiekosten?

@Service
public class TransactionService {

    private RootRepository rootRepository;

    private final Logger logger = LoggerFactory.getLogger(TransactionService.class);

    @Autowired
    public TransactionService(RootRepository rootRepository) {
        this.rootRepository = rootRepository;
    }

    public Transaction executeTransaction(Transaction transaction){
        //TODO: amount van verkoper checken, balance van koper checken,
        // transactie opslaan in database, balances bijwerken, asset amounts bijwerken, transactiekosten boeken.

        //return transaction;
        return null;
    }

    public Boolean checkAccountBalance(double transactionValue, String ibanBuyer) {
        //TODO: BankAccount saldo van koper vergelijken met transactiewaarde.

        return false;
    }

    public Boolean checkAssetAmount(double transactionAmount, String userName, String assetCode) {
        //TODO: Portfolio asset amount van verkoper vergelijken met transactie asset aantal.
        // uit transactie iban van verkoper halen en via Customer checken welke username om
        // in de juiste portfolio te kijken bij de juiste asset naar de beschikbare hoeveelheid

        return false;
    }

    public void adjustAccountBalances(double transactionValue,double transactionCost, String ibanBuyer, String ibanSeller){
        //TODO: saldo van koper en verkoper aanpassen
    }

    public void adjustPortfolioAmount() {
        //TODO: aantal van de asset bijwerken in portfolio van koper en verkoper.
    }



    public RootRepository getRootRepository() {
        return rootRepository;
    }
}
