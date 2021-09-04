package com.example.digital_gold.service;

import com.example.digital_gold.domain.Asset;
import com.example.digital_gold.domain.AssetPrice;
import com.example.digital_gold.domain.Portfolio;
import com.example.digital_gold.domain.PortfolioHistory;
import com.example.digital_gold.repository.RootRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/*
@Author Jany Gaal
*/

@Service
public class PortfolioOverviewService {

    private final RootRepository rootRepository;

    @Autowired
    public PortfolioOverviewService(RootRepository rootRepository) {
        this.rootRepository = rootRepository;
    }

    public List<PortfolioHistory> getOverviewAssets(String username) {
        return rootRepository.getPortfolioValuesForCustomer(username);
    }

    public Portfolio getPortfolioForCustomer (String username) {
        return rootRepository.getPortfolioForCustomer(username);
    }


    public double calculateDailyValue(Portfolio portfolio) {
       final Double[] totalValue = {0.00};
        Map<Asset, Double> assetMap = portfolio.getAssetList();
        assetMap.forEach((key, value) -> {
            AssetPrice assetPrice =rootRepository.findPriceByAssetCode(key.getAssetCode());
            double price = assetPrice.getPrice();
            double amount = value;
            totalValue[0] += (price * amount);
        });
        PortfolioHistory portfolioHistory = new PortfolioHistory(portfolio.getCustomer(), LocalDate.now(),totalValue[0]);
        rootRepository.savePortfolioValue(portfolioHistory);
        return totalValue[0];
    }
}




