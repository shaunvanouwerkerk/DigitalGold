package com.example.digital_gold.schedulingtasks;

import com.example.digital_gold.controller.AssetOverviewBankController;
import com.example.digital_gold.domain.*;
import com.example.digital_gold.repository.RootRepository;
import com.example.digital_gold.service.PortfolioOverviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

/**
* @author Jany Gaal
*/

@Component
public class Scheduler {

    private final RootRepository rootRepository;
    private final PortfolioOverviewService portfolioOverviewService;
    private final Random randomNumberGenerator = new Random();
    private final AssetOverviewBankController assetOverviewBankController;

    @Autowired
    public Scheduler(RootRepository rootRepository, PortfolioOverviewService portfolioOverviewService,
                     AssetOverviewBankController assetOverviewBankController) {
        this.rootRepository = rootRepository;
        this.portfolioOverviewService = portfolioOverviewService;
        this.assetOverviewBankController = assetOverviewBankController;
    }

    @Scheduled(cron = "0 54 15 * * *")
    public void testTask() {
        saveDailyPortfolioValues();
        //saveDailyAssetPrices();
    }

    @Scheduled(cron = "0 0/5 * * * *")
    public void testTask2() {
        saveCurrentAssetPrices();
    }

    public void saveDailyPortfolioValues() {
        List<Portfolio> portfolios = rootRepository.getAllPortfolios();
        for (Portfolio portfolio : portfolios) {
            double value = portfolioOverviewService.calculatePortfolioValue(portfolio);
            PortfolioHistory portfolioHistory = new PortfolioHistory(portfolio.getCustomer(), LocalDate.now(), value);
            savePortfolioValue(portfolioHistory);
        }
    }

    /*public void saveDailyAssetPrices() {
        List<Asset> assetList = rootRepository.findAllAssets();
        assetList.forEach(this::generateAssetPrices);
    }*/

    public void saveCurrentAssetPrices() {
        List<Asset> assetList = rootRepository.findAllAssets();
        assetList.forEach(this::getCurrentAssetPrices);
    }

    public void savePortfolioValue(PortfolioHistory portfolioHistory) {
        rootRepository.savePortfolioValue(portfolioHistory);
    }

   /* public void generateAssetPrices(Asset asset) {
        List<AssetPrice> assetPriceList = rootRepository.findPricesByAssetCode(asset.getAssetCode());
        AssetPrice yesterdaysAssetPrice = assetPriceList.get(assetPriceList.size() - 1);
        double yesterdaysPrice = yesterdaysAssetPrice.getPrice();
        double todaysPrice = yesterdaysPrice * (1 + randomNumberGenerator.nextDouble());
        AssetPrice todaysAssetPrice = new AssetPrice(asset, todaysPrice, LocalDate.now());
        saveAssetPrice(todaysAssetPrice);
    }*/

    public void getCurrentAssetPrices(Asset asset) {
        try {
            List<CryptoApiAssetPrice> assetPriceList = assetOverviewBankController.getAssetOverviewBank();
            for(CryptoApiAssetPrice price: assetPriceList) {
                AssetPrice assetPrice = new AssetPrice(asset, price.getCurrentPrice(), LocalDate.now()); // todo: convert to localdatetime
                saveAssetPrice(assetPrice);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void saveAssetPrice(AssetPrice assetPrice) {
        rootRepository.saveAssetPrice(assetPrice);
    }
}
