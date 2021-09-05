package com.example.digital_gold.repository;

/*
@Author Jany Gaal
*/

import java.util.List;

public interface PortfolioDao {

    int addPortfolioAsset (JdbcPortfolioDao.PortfolioDatabase portfolioDatabase);
    JdbcPortfolioDao.PortfolioDatabase updatePortfolioAsset (JdbcPortfolioDao.PortfolioDatabase portfolioDatabase);
    int deletePortfolioAsset (JdbcPortfolioDao.PortfolioDatabase portfolioDatabase);
    List<JdbcPortfolioDao.PortfolioDatabase> getPortfolioAssetsByUsername(String username);
    List<String> getAllUsersWithAPortfolio();
}
