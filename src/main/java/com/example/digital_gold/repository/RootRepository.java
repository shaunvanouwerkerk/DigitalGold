package com.example.digital_gold.repository;

import com.example.digital_gold.domain.*;
import com.example.digital_gold.service.PortfolioValueOverview;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.time.LocalDate;

@Repository
public class RootRepository {

    private final Logger logger = LoggerFactory.getLogger(RootRepository.class);

    private CustomerDao customerDao;
    private AdministratorDao administratorDao;
    private TransactionDao transactionDao;
    private AssetDao assetDao;
    private AssetPriceDao assetPriceDao;
    private PortfolioDao portfolioDao;
    private PortfolioHistoryDao portfolioHistoryDao;
    private BankAccountDao bankAccountDao;

    @Autowired
    public RootRepository(CustomerDao customerDao, AdministratorDao administratorDao, TransactionDao transactionDao,
                          AssetDao assetDao, AssetPriceDao assetPriceDao, PortfolioDao portfolioDao,
                          PortfolioHistoryDao portfolioHistoryDao) {
        this.customerDao = customerDao;
        this.administratorDao = administratorDao;
        this.transactionDao = transactionDao;
        this.assetDao = assetDao;
        this.assetPriceDao = assetPriceDao;
        this.portfolioDao = portfolioDao;
        this.bankAccountDao = bankAccountDao;
        this.portfolioHistoryDao = portfolioHistoryDao;
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

    public String findCustomerSalt(String username) {
        return customerDao.findCustomerSalt(username);
    }

    public String findAdministratorSalt(String username) {
        return administratorDao.findAdministratorSalt(username);
    }

    public String findCustomerHashPassword(String username) {
        return customerDao.findCustomerHashPassword(username);
    }

    public String findAdministratorHashPassword(String username) {
        return administratorDao.findAdministratorHashPassword(username);
    }

    public String findUsernameByIban(String iban) {return customerDao.findUsernameByIban(iban);}

    //TransactionDao
    public Transaction saveTransaction(Transaction transaction) {
        return transactionDao.saveTransaction(transaction);
    }

    // AssetDao
    public void saveAsset(Asset asset) {
        assetDao.saveAsset(asset);
    }
    public List<Asset> findAllAssets() { return assetDao.findAllAssets(); }
    public Asset findByAssetCode(String assetCode) {
        return assetDao.findByAssetCode(assetCode);
    }

    // AssetPriceDao
    public void saveAssetPrice(AssetPrice assetPrice) { assetPriceDao.saveAssetPrice(assetPrice); }
    public AssetPrice findPriceByAssetCodeAndDate(String assetCode, LocalDate date) { return assetPriceDao.findPriceByAssetCodeAndDate(assetCode, date); }
    public List<AssetPrice> findPricesByAssetCode(String assetCode) { return assetPriceDao.findPricesByAssetCode(assetCode); }
    public List<Map<String, Object>> findAllAvailableAssets(LocalDate today) { return assetPriceDao.findAllAvailableAssets(today); }

    public Portfolio savePortfolio(Portfolio portfolio) {
        for (Map.Entry<Asset, Double> entry : portfolio.getAssetList().entrySet()) {
            PortfolioDatabase portfolioDatabase = new PortfolioDatabase(portfolio.getCustomer().getUsername(),
                    entry.getKey().getAssetCode(), entry.getValue());
            portfolioDao.addPortfolioAsset(portfolioDatabase);
        }
        return portfolio;
    }

    public Portfolio updatePortfolio(Portfolio portfolio) {
        for (Map.Entry<Asset, Double> entry : portfolio.getAssetList().entrySet()) {
            PortfolioDatabase portfolioDatabase = new PortfolioDatabase(portfolio.getCustomer().getUsername(),
                    entry.getKey().getAssetCode(), entry.getValue());
            portfolioDao.updatePortfolioAsset(portfolioDatabase);
        }
        return portfolio;
    }

    public int deletePortfolio(Portfolio portfolio) {
        int result = 0;
        for (Map.Entry<Asset, Double> entry : portfolio.getAssetList().entrySet()) {
            PortfolioDatabase portfolioDatabase = new PortfolioDatabase(portfolio.getCustomer().getUsername(),
                    entry.getKey().getAssetCode(), entry.getValue());
            portfolioDao.deletePortfolioAsset(portfolioDatabase);
            result++;
        }
        return result;
    }

    // BankAccountDao
    public BankAccount saveBankAccount(BankAccount bankAccount) {
        return bankAccountDao.saveBankAccount(bankAccount);
    }

    public BankAccount updateBalance(BankAccount bankAccount) {
        return bankAccountDao.updateBalance(bankAccount);
    }

    public double getBalanceByIban(String iban) {return bankAccountDao.getBalanceByIban(iban);}

    public Portfolio getPortfolioForCustomer(String username) {
        List<PortfolioDatabase> tempList = portfolioDao.getPortfolioAssetsByUsername(username);
        Customer customer = null;
        Map<Asset, Double> assetMap = new HashMap<>();
        for (PortfolioDatabase p : tempList) {
            assetMap.put(assetDao.findByAssetCode(p.getAssetCode()), p.amount);
        }
        return new Portfolio(customer, assetMap);
    }

    public double getPortfolioAssetByUsernameAssetCode(String username, String assetCode) {
        return portfolioDao.getPortfolioAssetByUsernameAssetCode(username, assetCode);
    }

    public List<Portfolio> getAllPortfolios() {
        List<String> userList = portfolioDao.getAllUsersWithAPortfolio();
        List<Portfolio> portfolios = new ArrayList<>();
        for (String user : userList) {
            Customer customer = customerDao.findAndReturnCustomerByUsername(user);
            Portfolio portfolio = getPortfolioForCustomer(user);
            portfolio.setCustomer(customer);
            portfolios.add(portfolio);
        }
        return portfolios;
    }

    public int savePortfolioValue(PortfolioHistory portfolioHistory) {
        return portfolioHistoryDao.savePortfolioValue(portfolioHistory);
    }

    public List<PortfolioHistory> getPortfolioValuesForCustomer(String username) {
        return portfolioHistoryDao.getPortfolioValuesByUserName(username);
    }

}






