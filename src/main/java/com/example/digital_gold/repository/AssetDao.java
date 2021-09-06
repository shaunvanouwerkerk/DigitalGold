package com.example.digital_gold.repository;

import com.example.digital_gold.domain.Asset;

import java.util.List;

/**
 * @author Fiona Gray
 * */

public interface AssetDao {

    Asset saveAsset(Asset asset);
    Asset findByAssetCode(String assetCode);
    List<Asset> findAllAssets();

}
