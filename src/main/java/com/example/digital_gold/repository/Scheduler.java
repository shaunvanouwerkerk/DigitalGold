package com.example.digital_gold.repository;

import com.example.digital_gold.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
* @author Jany Gaal
*/

@Configuration
@EnableScheduling
public class Scheduler {

    private final RootRepository rootRepository;
    private final Logger logger = LoggerFactory.getLogger(Scheduler.class);
    private Random randomNumberGenerator = new Random();

    @Autowired
    public Scheduler(RootRepository rootRepository) {
        this.rootRepository = rootRepository;
        logger.info("DailyTask executed");
    }

    @Scheduled(cron = "0 37 16 * * *")
    public void testTask() {
        saveDailyAssetPrices();
        saveDailyPortfolioValues();

    }

    public void saveDailyPortfolioValues() {
        List<Portfolio> portfolios = rootRepository.getAllPortfolios();
        portfolios.forEach(this::calculateDailyValue);
    }

    public void saveDailyAssetPrices() {
        List<Asset> assetList = rootRepository.findAllAssets();
        assetList.forEach(this::generateAssetPrices);
    }

    public double calculateDailyValue(Portfolio portfolio) {
        final Double[] totalValue = {0.00};
        Map<Asset, Double> assetMap = portfolio.getAssetList();
        assetMap.forEach((key, value) -> {
            AssetPrice assetPrice = rootRepository.findPriceByAssetCodeAndDate(key.getAssetCode(), LocalDate.now());
            double price = assetPrice.getPrice();
            double amount = value;
            totalValue[0] += (price * amount);
        });
        PortfolioHistory portfolioHistory = new PortfolioHistory(portfolio.getCustomer(), LocalDate.now(), totalValue[0]);
        saveDailyValue(portfolioHistory);
        return totalValue[0];
    }

    public void saveDailyValue(PortfolioHistory portfolioHistory) {
        rootRepository.savePortfolioValue(portfolioHistory);
    }

    public void generateAssetPrices(Asset asset) {
        List<AssetPrice> assetPriceList = rootRepository.findPricesByAssetCode(asset.getAssetCode());
        AssetPrice yesterdaysAssetPrice = assetPriceList.get(assetPriceList.size() - 1);
        double yesterdaysPrice = yesterdaysAssetPrice.getPrice();
        double todaysPrice = yesterdaysPrice * (1 + randomNumberGenerator.nextDouble());
        AssetPrice todaysAssetPrice = new AssetPrice(asset, todaysPrice, LocalDate.now());
        saveGeneratedAssetPrice(todaysAssetPrice);
    }

    public void saveGeneratedAssetPrice(AssetPrice assetPrice) {
        rootRepository.saveAssetPrice(assetPrice);
    }
}
