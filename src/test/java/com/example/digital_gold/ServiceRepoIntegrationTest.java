package com.example.digital_gold;

import com.example.digital_gold.domain.Address;
import com.example.digital_gold.domain.Customer;
import com.example.digital_gold.domain.CustomerDetails;
import com.example.digital_gold.domain.FullName;
import com.example.digital_gold.repository.RootRepository;
import com.example.digital_gold.service.RegisterService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class ServiceRepoIntegrationTest {

   private RegisterService registerService;

   @Autowired
    public ServiceRepoIntegrationTest(RegisterService registerService) {
        this.registerService = registerService;
    }

    @Test
    public void registerServiceNotNull() {
       assertThat(registerService).isNotNull();
    }

    @Test
    public void rootRepositoryNotNull() {
       assertThat(registerService.getRootRepository()).isNotNull();
    }

    @Test
    public void registerServiceTest() {
        FullName testfullname = new FullName("Tester", "van", "Tester");
        Address testadress = new Address(1, "TestStraat", "1111AA", "TestCity");
        CustomerDetails testcustomerDetails = new CustomerDetails(Date.valueOf("1900-01-01"),"753654852",
                "tester@gmail.com" );
        Customer testcustomer = new Customer("TestUser10", "TestPassword", "zoutje", testfullname,
                testadress, testcustomerDetails);
        assertThat(registerService.register(testcustomer)).isEqualTo(testcustomer);
    }
}
