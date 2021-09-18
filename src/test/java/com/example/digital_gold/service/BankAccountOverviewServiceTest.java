package com.example.digital_gold.service;

import com.example.digital_gold.domain.*;
import com.example.digital_gold.repository.RootRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;

import java.sql.Date;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BankAccountOverviewServiceTest {

    private RootRepository mockRepo;
    private BankAccountOverviewService bankAccountOverviewService;

    @BeforeAll
    public void Setup() {
        mockRepo = Mockito.mock(RootRepository.class);
        bankAccountOverviewService = new BankAccountOverviewService(mockRepo);

        Customer testCustomer = new Customer("TestUser200", "TestPassword", "zoutje", true,
                new FullName("Tester", "van", "Tester"),
                new Address(1, "TestStraat", "1111AA", "TestCity"),
                new CustomerDetails(Date.valueOf("1950-01-01"),"753654852","tester@gmail.com","Nl181234567890"));

        BankAccount testBankAccount = new BankAccount("Nl181234567890", 2222.44);

        Mockito.when(mockRepo.findIbanByUsername("TestUser200")).thenReturn("Nl181234567890");
        Mockito.when(mockRepo.getBalanceByIban("Nl181234567890")).thenReturn(2222.44);
    }

    @AfterAll
    public void tearDown() {
        mockRepo = null;
        bankAccountOverviewService = null;
    }

    @Test
    public void bankAccountOverview() {
        BankAccount expected = new BankAccount("Nl181234567890", 2222.44);
        BankAccount actual = bankAccountOverviewService.bankAccountOverview("TestUser200");
        assertThat(actual).isEqualTo(expected);
    }




}
