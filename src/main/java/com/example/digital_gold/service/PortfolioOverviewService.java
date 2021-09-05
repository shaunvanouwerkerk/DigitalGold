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
}




