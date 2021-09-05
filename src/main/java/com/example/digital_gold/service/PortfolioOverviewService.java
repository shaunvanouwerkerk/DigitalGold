package com.example.digital_gold.service;

import com.example.digital_gold.domain.Portfolio;
import com.example.digital_gold.domain.PortfolioHistory;
import com.example.digital_gold.repository.RootRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public String[][] getPortfolioForCustomer2(String username) {
        return rootRepository.getPortfolioForCustomer2(username);
    }
}




