package com.example.digital_gold.repository;

import com.example.digital_gold.domain.Asset;
import com.example.digital_gold.domain.AssetPrice;

/**
 * @author Fiona Gray
 * */

public interface AssetPriceDao {

    AssetPrice saveAssetPrice(AssetPrice assetPrice);
    AssetPrice findPriceByAssetCode(String assetCode);

}
