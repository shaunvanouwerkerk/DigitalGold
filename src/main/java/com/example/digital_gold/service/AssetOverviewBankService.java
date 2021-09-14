package com.example.digital_gold.service;

import com.example.digital_gold.domain.Asset;
import com.example.digital_gold.domain.AssetPrice;
import com.example.digital_gold.domain.CryptoApiAssetPrice;
import com.example.digital_gold.repository.RootRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
* @author Fiona Gray
* */

@Service
public class AssetOverviewBankService {

    private RootRepository rootRepository;

    private final Logger logger = LoggerFactory.getLogger(AssetOverviewBankService.class);

    @Autowired
    public AssetOverviewBankService(RootRepository rootRepository) {
        this.rootRepository = rootRepository;
        logger.info("New AssetOverviewBankService");
    }

    public List<CryptoApiAssetPrice> getAndSaveTwentyPrices(List<CryptoApiAssetPrice> prices) {
        List<Asset> assets = rootRepository.findAllAssets();
        List<CryptoApiAssetPrice> twentyPrices = new ArrayList<>();
        for(Asset asset: assets) {
            for (CryptoApiAssetPrice price : prices) {
                if (price.getSymbol().toUpperCase(Locale.ROOT).equals(asset.getAssetCode())) {
                    twentyPrices.add(price);
                    double roundedAssetPrice = roundDouble(price.getCurrentPrice());
                    AssetPrice assetPrice = new AssetPrice(asset, roundedAssetPrice, LocalDateTime.now());
                    rootRepository.saveAssetPrice(assetPrice);
                }
            }
        }
        return twentyPrices;
    }

    public double roundDouble(double price) {
        double roundedPrice = Math.round(price * 1000.00) / 1000.00;
        return roundedPrice;
    }

// via randomgenerator
/*
    public List<Map<String, Object>> getAssetOverviewBank(LocalDate today) {
        List<Map<String, Object>> assetList = rootRepository.findAllAvailableAssets(today);
        if (!assetList.isEmpty()) {
            logger.info("AssetOverview is created");
            return assetList;
        } else {
            logger.info("AssetOverview could not be created");
            return null;
        }
    }*/
}
