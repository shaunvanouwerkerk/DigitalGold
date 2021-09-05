package com.example.digital_gold.controller;

import com.example.digital_gold.service.AssetOverviewBankService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(AssetOverviewBankController.class)
class AssetOverviewBankControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private AssetOverviewBankService assetOverviewBankService;

    @Autowired
    public AssetOverviewBankControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    // todo createTestOverview

    // todo createJsonOverview

    // TODO validRequest
    @Test
    void validRequestGetAssetOverviewBank() {
    }

    //TODO invalid Request
    @Test
    void invalidRequestGetAssetOverviewBank() {
    }

}