package com.example.digital_gold.repository;

import com.example.digital_gold.domain.Portfolio;

/*
@Author Jany Gaal
*/

public interface PortfolioDao {

    RootRepository.PortfolioDatabase addPortfolioAsset (RootRepository.PortfolioDatabase portfolioDatabase);
    RootRepository.PortfolioDatabase updatePortfolioAsset (RootRepository.PortfolioDatabase portfolioDatabase);
    RootRepository.PortfolioDatabase deletePortfolioAsset (RootRepository.PortfolioDatabase portfolioDatabase);
    RootRepository.PortfolioDatabase getPortfolioAssetsByUsername (String username);
    RootRepository.PortfolioDatabase getPortfolioAssetsPortfolioAsset (String username);


}
