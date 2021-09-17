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

/**
* @author Jany Gaal
*/

@Component
public class Scheduler {

    private final RootRepository rootRepository;
    private final PortfolioOverviewService portfolioOverviewService;
    private final AssetOverviewBankController assetOverviewBankController;

    @Autowired
    public Scheduler(RootRepository rootRepository, PortfolioOverviewService portfolioOverviewService,
                     AssetOverviewBankController assetOverviewBankController) {
        this.rootRepository = rootRepository;
        this.portfolioOverviewService = portfolioOverviewService;
        this.assetOverviewBankController = assetOverviewBankController;
    }

    @Scheduled(cron = "0 59 23 * * *")
    public void testTask() {
        saveDailyPortfolioValues();
    }

    @Scheduled(cron = "0 0/1 * * * *")
    public void testTask2() {
        getCurrentAssetPrices();
    }

    public void saveDailyPortfolioValues() {
        List<Portfolio> portfolios = rootRepository.getAllPortfolios();
        for (Portfolio portfolio : portfolios) {
            double value = portfolioOverviewService.calculatePortfolioValue(portfolio);
            PortfolioHistory portfolioHistory = new PortfolioHistory(portfolio.getCustomer(), LocalDate.now(), value);
            savePortfolioValue(portfolioHistory);
        }
    }

    public void saveCurrentAssetPrices() {
        // List<Asset> assetList = rootRepository.findAllAssets();
        //getCurrentAssetPrices();
        //assetList.forEach(this::getCurrentAssetPrices);
    }

    public void savePortfolioValue(PortfolioHistory portfolioHistory) {
        rootRepository.savePortfolioValue(portfolioHistory);
    }

    public void getCurrentAssetPrices() {
        try {
            //assetOverviewBankController.getAssetOverviewBank();
            //List<CryptoApiAssetPrice> assetPriceList = assetOverviewBankController.getAssetOverviewBank();
            /*for(Asset asset: assetList) {
                for (CryptoApiAssetPrice price: assetPriceList) {
                    if (price.getSymbol().toUpperCase(Locale.ROOT).equals(asset.getAssetCode())) {
                        AssetPrice assetPrice = new AssetPrice(asset, price.getCurrentPrice(), LocalDate.now()); // todo: convert to localdatetime
                        saveAssetPrice(assetPrice);
                    }
                }
            }*/
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /*public void saveAssetPrice(AssetPrice assetPrice) {
        rootRepository.saveAssetPrice(assetPrice);
    }*/
}
