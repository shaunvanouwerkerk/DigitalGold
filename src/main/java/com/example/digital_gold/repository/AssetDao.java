package com.example.digital_gold.repository;

import com.example.digital_gold.domain.Asset;

/**
 * @author Fiona Gray
 * */

public interface AssetDao {

    // todo eventueel returntype Asset meegeven aan save()
    void saveAsset(Asset asset);
    Asset findByAssetCode(String assetCode);
}
