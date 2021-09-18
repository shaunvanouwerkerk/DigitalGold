package com.example.digital_gold.repository;

import com.example.digital_gold.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

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
    private AministratorDashboardDao aministratorDashboardDao;

    @Autowired
    public RootRepository(CustomerDao customerDao, AdministratorDao administratorDao, TransactionDao transactionDao,
                          AssetDao assetDao, AssetPriceDao assetPriceDao, PortfolioDao portfolioDao,
                          PortfolioHistoryDao portfolioHistoryDao, BankAccountDao bankAccountDao, AministratorDashboardDao aministratorDashboardDao) {
        this.customerDao = customerDao;
        this.administratorDao = administratorDao;
        this.transactionDao = transactionDao;
        this.assetDao = assetDao;
        this.assetPriceDao = assetPriceDao;
        this.portfolioDao = portfolioDao;
        this.bankAccountDao = bankAccountDao;
        this.portfolioHistoryDao = portfolioHistoryDao;
        this.aministratorDashboardDao = aministratorDashboardDao;
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
    public Customer findAndReturnCustomerByUsername(String username) {
        return customerDao.findAndReturnCustomerByUsername(username);
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

    public String findUsernameByIban(String iban) {
        return customerDao.findUsernameByIban(iban);
    }

    public String findIbanByUsername(String username) {
        return customerDao.findIbanByUsername(username);
    }


    //TransactionDao
    public Transaction saveTransaction(Transaction transaction) {
        return transactionDao.saveTransaction(transaction);
    }

    // AssetDao
    public List<Asset> findAllAssets() {
        return assetDao.findAllAssets();
    }
    public Asset findAssetByAssetCode(String assetCode) {
        return assetDao.findAssetByAssetCode(assetCode);
    }

    // AssetPriceDao
    public void saveAssetPrice(AssetPrice assetPrice) { assetPriceDao.saveAssetPrice(assetPrice); }
    public AssetPrice findAssetPriceByAssetCode(String assetCode) { return assetPriceDao.findAssetPriceByAssetCode(assetCode); }
    public List<AssetPrice> findAllAssetPrices() { return assetPriceDao.findAllAssetPrices(); }

    // BankAccountDao
    public BankAccount saveBankAccount(BankAccount bankAccount) {
        return bankAccountDao.saveBankAccount(bankAccount);
    }

    public BankAccount updateBalance(BankAccount bankAccount) {
        return bankAccountDao.updateBalance(bankAccount);
    }

    public double getBalanceByIban(String iban) {
        return bankAccountDao.getBalanceByIban(iban);
    }

    // PortfolioDao
    public Portfolio getPortfolioForCustomer(String username) {
        List<PortfolioDatabase> tempList = portfolioDao.getPortfolioAssetsByUsername(username);
        Customer customer = customerDao.findAndReturnCustomerByUsername(username);
        Map<Asset, Double> tempMap = new HashMap<>();
        tempList.forEach(object -> {
            tempMap.put(assetDao.findAssetByAssetCode(
                    object.getAssetCode()),
                    object.amount);
        });
        return new Portfolio(customer, tempMap);
    }

    public double getPortfolioAssetByUsernameAssetCode(String username, String assetCode) {
        return portfolioDao.getPortfolioAssetByUsernameAssetCode(username, assetCode);
    }

    public List<Portfolio> getAllPortfolios() {
        List<String> userList = portfolioDao.getAllUsersWithAPortfolio();
        List<Portfolio> portfolioList = new ArrayList<>();
        userList.forEach(user -> {
            Customer customer = customerDao.findAndReturnCustomerByUsername(user);
            Portfolio portfolio = getPortfolioForCustomer(user);
            portfolio.setCustomer(customer);
            portfolioList.add(portfolio);
        });
        return portfolioList;
    }

    public void savePortfolioValue(PortfolioHistory portfolioHistory) {
        portfolioHistoryDao.savePortfolioValue(portfolioHistory);
    }

    public List<PortfolioHistory> getPortfolioValuesForCustomer(String username) {
        return portfolioHistoryDao.getPortfolioValuesByUserName(username);
    }

    public void saveAssetChangesInPortfolio(Portfolio portfolio) {
        List<PortfolioDatabase> tempList = new ArrayList<>();
        portfolio.getAssetList().forEach((key, value) -> {
            tempList.add(new PortfolioDatabase
                    (portfolio.getCustomer().getUsername(),
                    key.getAssetCode(),
                    value));
            updatePortfolio(tempList);
        });
    }

    public void updatePortfolio(List<PortfolioDatabase> list) {
        list.forEach(object -> {
            if (object.amount == 0.00) {
                portfolioDao.deletePortfolioAsset(object);
            } else if (portfolioDao.getPortfolioAssetByUsernameAssetCode
                    (object.username, object.assetCode) == 0.00) {
                portfolioDao.addPortfolioAsset(object);
            } else {
                portfolioDao.updatePortfolioAsset(object);
            }
        });
    }

    //AdministratorDasboardDao

    public double findStartingBudgetByUsername (String username){
        return aministratorDashboardDao.findStartingBudgetByUsername(username);
    }

    public double findTransactionFeeByUsername (String username){
    return aministratorDashboardDao.findTransactionFeeByUsername(username);
    }

    public void updateStartingBudget(AdministratorDashboard administratorDashboard){
        aministratorDashboardDao.updateStartingBudget(administratorDashboard);
    }

    public void updateTransactionFee (AdministratorDashboard administratorDashboard){
        aministratorDashboardDao.updateTransactionFee(administratorDashboard);
    }


}








