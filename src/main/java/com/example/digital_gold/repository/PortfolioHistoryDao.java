package com.example.digital_gold.repository;

import com.example.digital_gold.domain.PortfolioHistory;

import java.util.List;

/*
@Author Jany Gaal
*/

public interface PortfolioHistoryDao {

    int savePortfolioValue(PortfolioHistory portfolioHistory);
    List<PortfolioHistory> getPortfolioValuesByUserName(String username);
}
