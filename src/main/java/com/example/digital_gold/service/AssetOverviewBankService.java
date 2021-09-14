package com.example.digital_gold.service;

import com.example.digital_gold.domain.Asset;
import com.example.digital_gold.domain.AssetPrice;
import com.example.digital_gold.domain.CryptoApiAssetPrice;
import com.example.digital_gold.repository.RootRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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

    public List<CryptoApiAssetPrice> getTwentyPrices(List<CryptoApiAssetPrice> prices) {
        List<Asset> assets = rootRepository.findAllAssets();
        List<CryptoApiAssetPrice> twentyPrices = new ArrayList<>();
        for(Asset asset: assets) {
            for (CryptoApiAssetPrice price : prices) {
                if (price.getSymbol().toUpperCase(Locale.ROOT).equals(asset.getAssetCode())) {
                    twentyPrices.add(price);
                    AssetPrice assetPrice = new AssetPrice(asset, price.getCurrentPrice(), LocalDate.now());
                    rootRepository.saveAssetPrice(assetPrice);
                }
            }
        }
        return twentyPrices;
    }

/*// todo: throw-catch sql exception?
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
