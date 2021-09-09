package com.example.digital_gold.repository;

/*
@Author Jany Gaal
*/

import java.util.List;

public interface PortfolioDao {

    int addPortfolioAsset (PortfolioDatabase portfolioDatabase);
    int updatePortfolioAsset (PortfolioDatabase portfolioDatabase);
    int deletePortfolioAsset (PortfolioDatabase portfolioDatabase);
    List<PortfolioDatabase> getPortfolioAssetsByUsername(String username);
    List<String> getAllUsersWithAPortfolio();
    double getPortfolioAssetByUsernameAssetCode (String username, String assetCode);
}
