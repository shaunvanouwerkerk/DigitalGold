package com.example.digital_gold.service;

import com.example.digital_gold.domain.AssetPrice;
import com.example.digital_gold.repository.RootRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
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

    public List<AssetPrice> getAssetOverviewBank(LocalDate today) {
        List<AssetPrice> assetList = rootRepository.findAllAvailableAssets(today);
        if (assetList.size() > 0) {
            return assetList;
        } else {
            return null;
        }
    }
}
