package com.example.digital_gold.repository;

import com.example.digital_gold.domain.AssetPrice;

/**
 * @author Fiona Gray
 * */

public interface AssetPriceDao {

    void saveAssetPrice(AssetPrice assetPrice);
    AssetPrice findPriceByAssetCode(String assetCode);

}
