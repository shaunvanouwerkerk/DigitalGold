package com.example.digital_gold.schedulingtasks;

import com.example.digital_gold.domain.Asset;
import com.example.digital_gold.domain.AssetPrice;
import com.example.digital_gold.domain.Portfolio;
import com.example.digital_gold.domain.PortfolioHistory;
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

    @Autowired
    public Scheduler(RootRepository rootRepository, PortfolioOverviewService portfolioOverviewService) {
        this.rootRepository = rootRepository;
        this.portfolioOverviewService = portfolioOverviewService;
    }

    @Scheduled(cron = "0 54 15 * * *")
    public void testTask() {
        saveDailyAssetPrices();
        saveDailyPortfolioValues();
    }

    public void saveDailyPortfolioValues() {
        List<Portfolio> portfolios = rootRepository.getAllPortfolios();
        for (Portfolio portfolio : portfolios) {
            double value = portfolioOverviewService.calculatePortfolioValue(portfolio);
            PortfolioHistory portfolioHistory = new PortfolioHistory(portfolio.getCustomer(), LocalDate.now(), value);
            savePortfolioValue(portfolioHistory);
        }
    }

    public void saveDailyAssetPrices() {
        List<Asset> assetList = rootRepository.findAllAssets();
        assetList.forEach(this::generateAssetPrices);
    }


    public void savePortfolioValue(PortfolioHistory portfolioHistory) {
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
