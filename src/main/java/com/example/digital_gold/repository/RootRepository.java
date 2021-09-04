package com.example.digital_gold.repository;

import com.example.digital_gold.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class RootRepository {

    private final Logger logger = LoggerFactory.getLogger(RootRepository.class);

    private CustomerDao customerDao;
    private AdministratorDao administratorDao;
    private TransactionDao transactionDao;
    private AssetDao assetDao;
    private AssetPriceDao assetPriceDao;
    private PortfolioDao portfolioDao;
    private BankAccountDao bankAccountDao;

    @Autowired
    public RootRepository(CustomerDao customerDao, AdministratorDao administratorDao, TransactionDao transactionDao,
                          AssetDao assetDao, AssetPriceDao assetPriceDao, PortfolioDao portfolioDao, BankAccountDao bankAccountDao) {
        this.customerDao = customerDao;
        this.administratorDao = administratorDao;
        this.transactionDao = transactionDao;
        this.assetDao = assetDao;
        this.assetPriceDao = assetPriceDao;
        this.portfolioDao = portfolioDao;
        this.bankAccountDao = bankAccountDao;
        logger.info("New RootRepository");
    }

    // CustomerDao
    public Customer saveCustomer(Customer customer) {
        return customerDao.save(customer);
    }
    public Administrator saveAdministrator(Administrator administrator) {
        return administratorDao.save(administrator);
    }

    public boolean findCustomerByUsernameAndEmail(String username, String emailAddress) {
        return customerDao.findCustomerByUsernameAndEmail(username, emailAddress);
    }

    public boolean findAdministratorByUsername(String username) {
        return administratorDao.findAdministratorByUsername(username);
    }
    public String findCustomerSalt(String username){return customerDao.findCustomerSalt(username); }
    public String findAdministratorSalt(String username){return administratorDao.findAdministratorSalt(username); }

    public String findCustomerHashPassword(String username){return customerDao.findCustomerHashPassword(username);}
    public String findAdministratorHashPassword(String username){return administratorDao.findAdministratorHashPassword(username);}

    public Transaction saveTransaction(Transaction transaction) {
        return transactionDao.saveTransaction(transaction);
    }

    // AssetDao
    public void saveAsset(Asset asset) { assetDao.saveAsset(asset); }

    public Asset findByAssetCode(String assetCode) { return assetDao.findByAssetCode(assetCode); }

    // AssetPriceDao
    public void saveAssetPrice(AssetPrice assetPrice) { assetPriceDao.saveAssetPrice(assetPrice); }

    public AssetPrice findPriceByAssetCode(String assetCode) { return assetPriceDao.findPriceByAssetCode(assetCode); }

    public Portfolio savePortfolio (Portfolio portfolio){
        for(Map.Entry<Asset, Double> entry : portfolio.getAssetList().entrySet()) {
            JdbcPortfolioDao.PortfolioDatabase portfolioDatabase = new JdbcPortfolioDao.PortfolioDatabase(portfolio.getCustomer().getUsername(),
                    entry.getKey().getAssetCode(), entry.getValue());
            portfolioDao.addPortfolioAsset(portfolioDatabase);
        }
        return portfolio;
    }

    public Portfolio updatePortfolio (Portfolio portfolio){
        for(Map.Entry<Asset, Double> entry : portfolio.getAssetList().entrySet()) {
            JdbcPortfolioDao.PortfolioDatabase portfolioDatabase = new JdbcPortfolioDao.PortfolioDatabase(portfolio.getCustomer().getUsername(),
                    entry.getKey().getAssetCode(), entry.getValue());
            portfolioDao.updatePortfolioAsset(portfolioDatabase);
        }
        return portfolio;
    }

    public int deletePortfolio (Portfolio portfolio){
        int result = 0;
        for(Map.Entry<Asset, Double> entry : portfolio.getAssetList().entrySet()) {
            JdbcPortfolioDao.PortfolioDatabase portfolioDatabase = new JdbcPortfolioDao.PortfolioDatabase(portfolio.getCustomer().getUsername(),
                    entry.getKey().getAssetCode(), entry.getValue());
            portfolioDao.deletePortfolioAsset(portfolioDatabase);
            result++;
        }
        return result;
    }
    // BankAccountDao
    public BankAccount saveBankAccount(BankAccount bankAccount) {return bankAccountDao.saveBankAccount(bankAccount);}

    public BankAccount updateBalance(BankAccount bankAccount) {return bankAccountDao.updateBalance(bankAccount);}

    }