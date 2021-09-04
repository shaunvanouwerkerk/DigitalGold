package com.example.digital_gold.controller;

import com.example.digital_gold.domain.*;
import com.example.digital_gold.service.RegisterService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.sql.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(RegisterController.class)
public class RegisterControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private RegisterService registerServiceMock;

    @Autowired
    public RegisterControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    private Customer createTestCustomer(){
        FullName testfullname = new FullName("Tester", "van", "De Test");
        Address testadress = new Address(1, "TestStraat", "1111AA", "TestCity");
        CustomerDetails testcustomerDetails = new CustomerDetails(Date.valueOf("1900-01-01"),"987654321",
                "tester@tester.tst" ,"NL 123456789");
        return new Customer("TestUser01", "TestPassword", testfullname, testadress, testcustomerDetails);
    }

    private String createJSONfromCustomer() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(createTestCustomer());
    }

    @Test
    public void registerValidRequestTest() throws Exception{

       when(registerServiceMock.register(any(Customer.class))).thenReturn(createTestCustomer());

        MockHttpServletRequestBuilder postRequest = MockMvcRequestBuilders.post("/register");
        postRequest.contentType(MediaType.APPLICATION_JSON)
                .content(createJSONfromCustomer());
        try {
            mockMvc.perform(postRequest).andExpect(status().isCreated());
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    @Test
    public void registerExistingUsernameRequestTest() throws Exception{

        when(registerServiceMock.register(any(Customer.class))).thenReturn(null);

        MockHttpServletRequestBuilder postRequest = MockMvcRequestBuilders.post("/register");
        postRequest.contentType(MediaType.APPLICATION_JSON)
                .content(createJSONfromCustomer());
        try {
            mockMvc.perform(postRequest).andExpect(status().isBadRequest())
                    .andExpect(content().string("Registration failed, username or emailaddress already exists"));
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    @Test
    public void registerInvalidRequestTest() throws Exception{

        when(registerServiceMock.register(any(Customer.class))).thenReturn(createTestCustomer());

        MockHttpServletRequestBuilder postRequest = MockMvcRequestBuilders.post("/register");
        postRequest.contentType(MediaType.APPLICATION_JSON)
                .content("this is an invalid request");
        try {
            mockMvc.perform(postRequest).andExpect(status().is4xxClientError());
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}