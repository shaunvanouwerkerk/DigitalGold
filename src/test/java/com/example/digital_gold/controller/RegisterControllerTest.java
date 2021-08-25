package com.example.digital_gold.controller;

import com.example.digital_gold.domain.Address;
import com.example.digital_gold.domain.Customer;
import com.example.digital_gold.domain.CustomerDetails;
import com.example.digital_gold.domain.FullName;
import com.example.digital_gold.service.RegisterService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(RegisterController.class)
public class RegisterControllerTest {

    @MockBean
    private RegisterService registerService;

    private MockMvc mockMvc;

    @Autowired
    public RegisterControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    public void registerTest() {
        FullName testfullname = new FullName("Tester", "van", "Tester");
        Address testadress = new Address(1, "TestStraat", "1111AA", "TestCity");
        CustomerDetails testcustomerDetails = new CustomerDetails(Date.valueOf("1900-01-01"),"753654852",
                "tester@gmail.com" );
        Customer testcustomer = new Customer("TestUser01", "TestPassword", "zoutje", testfullname, testadress, testcustomerDetails);

/*
        Mockito.when(registerService.register("TestUser01", "TestPassword", "Tester", "van", "Tester", 1, "TestStraat", "1111AA",
                 "TestCity","1900-01-01", "753654852", "tester@gmail.com")).thenReturn(testcustomer);
*/


    }
}