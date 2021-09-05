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

/*
@Author Jany Gaal
*/

@Configuration
@EnableScheduling
public class Scheduler {

    private final RootRepository rootRepository;
    private final Logger logger = LoggerFactory.getLogger(Scheduler.class);

    @Autowired
    public Scheduler(RootRepository rootRepository) {
        this.rootRepository = rootRepository;
        logger.info("DailyTask executed");
    }

    @Scheduled(cron = "0 59 23 * * *")
    public void testTask() {
        saveDailyPortfolioValues();
    }

    public void saveDailyPortfolioValues () {
        List<Portfolio> portfolios = rootRepository.getAllPortfolios();
        portfolios.forEach(this::calculateDailyValue);
    }

    public void calculateDailyValue(Portfolio portfolio) {
        final Double[] totalValue = {0.00};
        Map<Asset, Double> assetMap = portfolio.getAssetList();
        assetMap.forEach((key, value) -> {
            AssetPrice assetPrice = rootRepository.findPriceByAssetCode(key.getAssetCode());
            double price = assetPrice.getPrice();
            double amount = value;
            totalValue[0] += (price * amount);
        });
        PortfolioHistory portfolioHistory = new PortfolioHistory(portfolio.getCustomer(), LocalDate.now(),totalValue[0]);
        rootRepository.savePortfolioValue(portfolioHistory);
    }
}
