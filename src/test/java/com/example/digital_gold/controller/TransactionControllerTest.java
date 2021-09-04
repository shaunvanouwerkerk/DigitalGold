package com.example.digital_gold.controller;

import com.example.digital_gold.domain.Transaction;
import com.example.digital_gold.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransactionController.class)
class TransactionControllerTest {
    private MockMvc mockMvc;

    @MockBean
    private TransactionService transactionService;

    @Autowired
    public TransactionControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    private Transaction createTestTransaction(){
        Transaction testTransaction =new Transaction();
        testTransaction.setAssetCode("XRP");
        testTransaction.setAssetAmount(200);
        testTransaction.setAssetPrice(1.28);
        testTransaction.setTransactionFee(0.01);
        testTransaction.setIbanSell("NL64DIGO0001734105");
        testTransaction.setIbanBuy("NL52DEUT0009374258");
        return testTransaction;
    }

    private String createJSONTransaction() throws Exception {
        return new String("{\"assetCode\":\"XRP\",\n" +
                "\"assetAmount\":\"200\",\n" +
                "\"assetPrice\":\"1.28\",\n" +
                "\"transactionFee\":\"0.01\",\n" +
                "\"ibanSell\":\"NL64DIGO0001734105\",\n" +
                "\"ibanBuy\":\"NL52DEUT0009374258\"\n" +
                "}");
    }

    @Test
    public void processValidTransactionTest() throws Exception{

        when(transactionService.processTransaction(any(Transaction.class))).thenReturn(createTestTransaction());

        MockHttpServletRequestBuilder postRequest = MockMvcRequestBuilders.post("/transaction");
        postRequest.contentType(MediaType.APPLICATION_JSON)
                .content(createJSONTransaction());
        try {
            mockMvc.perform(postRequest).andExpect(status().isCreated());
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    @Test
    public void processInvalidTransactionTest() throws Exception{

        when(transactionService.processTransaction(any(Transaction.class))).thenReturn(null);

        MockHttpServletRequestBuilder postRequest = MockMvcRequestBuilders.post("/transaction");
        postRequest.contentType(MediaType.APPLICATION_JSON)
                .content(createJSONTransaction());
        try {
            mockMvc.perform(postRequest).andExpect(status().isBadRequest())
                    .andExpect(content().string("Transaction could not be processed."));
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}