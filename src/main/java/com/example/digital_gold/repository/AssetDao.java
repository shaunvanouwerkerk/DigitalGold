package com.example.digital_gold.repository;

import com.example.digital_gold.domain.Asset;

/**
 * @author Fiona Gray
 * */

public interface AssetDao {

    Asset saveAsset(Asset asset);
    Asset findByAssetCode(String assetCode);

    // todo evt. deleteAsset/updateAsset
}
