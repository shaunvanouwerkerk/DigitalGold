package com.example.digital_gold.service;

import com.example.digital_gold.domain.Asset;
import com.example.digital_gold.domain.AssetPrice;
import com.example.digital_gold.domain.Portfolio;
import com.example.digital_gold.domain.PortfolioHistory;
import com.example.digital_gold.repository.RootRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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


    public PortfolioValueOverview getPortfolioOverviewToday(String username) {
        try {
            Portfolio portfolio = rootRepository.getPortfolioForCustomer(username);
            return new PortfolioValueOverview(LocalDate.now(), calculatePortfolioValue(portfolio));
        } catch (DataAccessException e) {
            return null;
        }
    }

    public List<PortfolioValueOverview> getPortfolioOverview(String username) {
        try {
            List<PortfolioHistory> values = rootRepository.getPortfolioValuesForCustomer(username);
            List<PortfolioValueOverview> portfolioValueOverviewList = new ArrayList<>();
            for (PortfolioHistory portfolioHistory : values) {
                PortfolioValueOverview portfolioValueOverview =
                        new PortfolioValueOverview(portfolioHistory.getDate(), portfolioHistory.getTotalvalue());
                portfolioValueOverviewList.add(portfolioValueOverview);
            }
            return portfolioValueOverviewList;
        } catch (DataAccessException e) {
            return null;
        }
    }

    public List<PortfolioAssetOverview> getPortfolioOverviewAssets(String username) {
        try {
            Portfolio portfolio = rootRepository.getPortfolioForCustomer(username);
            Map<Asset, Double> map = portfolio.getAssetList();
            List<PortfolioAssetOverview> list = new ArrayList<>();
            map.forEach((key, value) -> {
                String assetName = key.getAssetName();
                String assetCode = key.getAssetCode();
                //double currentPrice = rootRepository.findPriceByAssetCodeAndDate(key.getAssetCode(), LocalDateTime.now()).getPrice();
                double currentPrice = rootRepository.findPriceByAssetCode(assetCode).getPrice();
                double amountOfAsset = value;
                double assetTotalValue = (currentPrice * amountOfAsset);
                PortfolioAssetOverview portfolioAssetOverview =
                        new PortfolioAssetOverview(assetName, assetCode, currentPrice, amountOfAsset, assetTotalValue);
                list.add(portfolioAssetOverview);
            });
            return list;
        } catch (DataAccessException e) {
            return null;
        }
    }

    public double calculatePortfolioValue(Portfolio portfolio) {
        final Double[] totalValue = {0.00};
        Map<Asset, Double> assetMap = portfolio.getAssetList();
        assetMap.forEach((key, value) -> {
            AssetPrice assetPrice = rootRepository.findPriceByAssetCode(key.getAssetCode());
            //AssetPrice assetPrice = rootRepository.findPriceByAssetCodeAndDate(key.getAssetCode(), LocalDateTime.now());
            double price = assetPrice.getPrice();
            double amount = value;
            totalValue[0] += (price * amount);
        });
        return totalValue[0];
    }
}




