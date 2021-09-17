package com.example.digital_gold.repository;

import com.example.digital_gold.domain.AssetPrice;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @author Fiona Gray
 * */

public interface AssetPriceDao {

    AssetPrice saveAssetPrice(AssetPrice assetPrice);
    AssetPrice findAssetPriceByAssetCode(String assetCode);
    List<AssetPrice> findAllAssetPrices();

}
