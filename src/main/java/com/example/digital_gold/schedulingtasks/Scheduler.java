package com.example.digital_gold.schedulingtasks;

import com.example.digital_gold.controller.AssetOverviewBankController;
import com.example.digital_gold.domain.*;
import com.example.digital_gold.repository.RootRepository;
import com.example.digital_gold.service.AssetOverviewBankService;
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
    private final AssetOverviewBankService assetOverviewBankService;

    @Autowired
    public Scheduler(RootRepository rootRepository, PortfolioOverviewService portfolioOverviewService,
                     AssetOverviewBankService assetOverviewBankService) {
        this.rootRepository = rootRepository;
        this.portfolioOverviewService = portfolioOverviewService;
        this.assetOverviewBankService = assetOverviewBankService;
    }

    @Scheduled(cron = "0 49 18 * * *")
    public void testTask() {
        saveDailyPortfolioValues();
    }

    @Scheduled(cron = "0 0/1 * * * *")
    public void testTask2() {
        getAndSaveCurrentAssetPrices();
    }

    public void saveDailyPortfolioValues() {
        List<Portfolio> portfolios = rootRepository.getAllPortfolios();
        for (Portfolio portfolio : portfolios) {
            double value = portfolioOverviewService.calculatePortfolioValue(portfolio);
            PortfolioHistory portfolioHistory = new PortfolioHistory(portfolio.getCustomer(), LocalDate.now(), value);
            savePortfolioValue(portfolioHistory);
        }
    }

    public void savePortfolioValue(PortfolioHistory portfolioHistory) {
        rootRepository.savePortfolioValue(portfolioHistory);
    }

    public void getAndSaveCurrentAssetPrices() {
        try { assetOverviewBankService.getAndSaveCryptoApiPrices();
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
