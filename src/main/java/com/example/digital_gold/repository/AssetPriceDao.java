package com.example.digital_gold.repository;

import com.example.digital_gold.domain.Asset;
import com.example.digital_gold.domain.AssetPrice;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * @author Fiona Gray
 * */

public interface AssetPriceDao {

    AssetPrice saveAssetPrice(AssetPrice assetPrice);
    AssetPrice findPriceByAssetCode(String assetCode);
    List<AssetPrice>  findAllAvailableAssets(LocalDate today);

}
