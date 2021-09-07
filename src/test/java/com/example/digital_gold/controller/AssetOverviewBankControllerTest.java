package com.example.digital_gold.controller;

import com.example.digital_gold.domain.AssetPrice;
import com.example.digital_gold.domain.Customer;
import com.example.digital_gold.service.AssetOverviewBankService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AssetOverviewBankController.class)
class AssetOverviewBankControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private AssetOverviewBankService assetOverviewBankServiceMock;

    @Autowired
    public AssetOverviewBankControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    private List<Map<String, Object>> createTestOverview() {
        List<Map<String, Object>> testOverview = new ArrayList<>();
        Map<String, Object> testAssetPrice1 = new HashMap<>();
        Map<String, Object> testAssetPrice2 = new HashMap<>();
        testAssetPrice1.put("ASSETCODE", "ADA");
        testAssetPrice1.put("PRICE", 30.75);
        testAssetPrice2.put("ASSETCODE", "VET");
        testAssetPrice2.put("PRICE", 76.89);
        testOverview.add(testAssetPrice1);
        testOverview.add(testAssetPrice2);
        return testOverview;
    }

    private String createJsonfromAssetOverviewBank() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(createTestOverview());
    }

    @Test
    public void validRequestGetAssetOverviewBank() {
        List<Map<String, Object>> assetOverview = createTestOverview();
        Mockito.when(assetOverviewBankServiceMock.getAssetOverviewBank(Mockito.any(LocalDate.class))).thenReturn(assetOverview);

        MockHttpServletRequestBuilder getRequest = MockMvcRequestBuilders.get("/assetOverviewBank");

        try {
            ResultActions response = mockMvc.perform(getRequest).andExpect(status().isOk());
            String responseBody = response.andReturn().getResponse().getContentAsString();
            assertThat(responseBody).isEqualTo(createJsonfromAssetOverviewBank());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    //TODO invalid Request
    @Test
    public void invalidRequestGetAssetOverviewBank() {

    }
}