package com.example.digital_gold.service;
/**
 * @Author Shaun & Sandra
 */

import com.example.digital_gold.domain.Address;
import com.example.digital_gold.domain.Customer;
import com.example.digital_gold.domain.CustomerDetails;
import com.example.digital_gold.domain.FullName;
import com.example.digital_gold.helper.SaltMaker;
import com.example.digital_gold.repository.RootRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.test.context.ActiveProfiles;

import java.sql.Date;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
class RegisterServiceTest {

    private RegisterService registerService;
    private RootRepository mockRepo;
    private SaltMaker mockSaltMaker;
    private HashService mockHashService;

    @BeforeAll
    public void setUp(){
        mockRepo = Mockito.mock(RootRepository.class);
        mockSaltMaker = Mockito.mock(SaltMaker.class);
        mockHashService = Mockito.mock(HashService.class);
        registerService = new RegisterService(mockRepo,mockSaltMaker,mockHashService);

    }
    @AfterAll
    public void tearDown() {
        mockRepo = null;
        mockSaltMaker = null;
        mockHashService = null;
        registerService = null;
    }

//todo Test werkt nog niet correct, komt steeds null uit;
    @Test
    void register() {
        Customer customer1 = new Customer("Test123", "welkom01","salt",new FullName("Jan", "van", "Ridder"),
                new Address(25,"Kerkstraat","1059 AT","Amsterdam"),new CustomerDetails(Date.valueOf("1995-01-01"),
                "123456789","janvanridder@gmail.com"));
        Customer customer2 = registerService.register(customer1);
//        Assertions.assertThat(customer1.getSalt()).isEqualTo(null);
//        Assertions.assertThat(customer2.getSalt()).isNotEmpty();

    }
}