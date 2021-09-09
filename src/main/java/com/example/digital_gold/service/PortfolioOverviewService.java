package com.example.digital_gold.service;

import com.example.digital_gold.domain.Asset;
import com.example.digital_gold.domain.Portfolio;
import com.example.digital_gold.domain.PortfolioHistory;
import com.example.digital_gold.repository.RootRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
@Author Jany Gaal
*/

@Service
public class PortfolioOverviewService {

    private RootRepository rootRepository;

    @Autowired
    public PortfolioOverviewService(RootRepository rootRepository) {
        this.rootRepository = rootRepository;
    }

    public PortfolioValueOverview getPortfolioOverviewToday(String username) {
        List<PortfolioHistory> values = rootRepository.getPortfolioValuesForCustomer(username);
        for (PortfolioHistory portfolioHistory : values) {
            if (!values.isEmpty()) {
                if (portfolioHistory.getDate().equals(LocalDate.now())) {
                    return new PortfolioValueOverview(portfolioHistory.getDate(), portfolioHistory.getTotalvalue());
                }
            }
        }
        return null;
    }

    public List<PortfolioValueOverview> getPortfolioOverview(String username) {
        List<PortfolioHistory> values = rootRepository.getPortfolioValuesForCustomer(username);
        List<PortfolioValueOverview> portfolioValueOverviewList = new ArrayList<>();
        for (PortfolioHistory portfolioHistory : values) {
            PortfolioValueOverview portfolioValueOverview =
                    new PortfolioValueOverview(portfolioHistory.getDate(), portfolioHistory.getTotalvalue());
            portfolioValueOverviewList.add(portfolioValueOverview);
        }
        return portfolioValueOverviewList;
    }

    public List<PortfolioAssetOverview> getPortfolioOverviewAssets(String username) {
        Portfolio portfolio = rootRepository.getPortfolioForCustomer(username);
        Map<Asset, Double> map = portfolio.getAssetList();
        List<PortfolioAssetOverview> list = new ArrayList<>();
        map.forEach((key, value) -> {
            String assetName = key.getAssetName();
            String assetCode = key.getAssetCode();
            double currentPrice = rootRepository.findPriceByAssetCodeAndDate(key.getAssetCode(), LocalDate.now()).getPrice();
            double amountOfAsset = value;
            double assetTotalValue = (currentPrice * amountOfAsset);
            PortfolioAssetOverview portfolioAssetOverview =
                    new PortfolioAssetOverview(assetName, assetCode, currentPrice, amountOfAsset, assetTotalValue);
            list.add(portfolioAssetOverview);
        });
        return list;
    }
}




