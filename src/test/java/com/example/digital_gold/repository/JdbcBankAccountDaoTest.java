package com.example.digital_gold.repository;

import com.example.digital_gold.domain.BankAccount;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class JdbcBankAccountDaoTest {

    private BankAccountDao bankAccountDaoTest;

    @Autowired
    public JdbcBankAccountDaoTest(BankAccountDao bankAccountDaoTest) {this.bankAccountDaoTest = bankAccountDaoTest; }

    @Test
    public void bankAccountDaoNotNull() {
        assertThat(bankAccountDaoTest).isNotNull();
    }

    BankAccount  testAccount = new BankAccount("NL13DIGO6940102114", 93580.35);

    @Test
    public void saveBankAccount() {
        BankAccount actualAccount = bankAccountDaoTest.saveBankAccount(testAccount);
        assertThat(actualAccount).isEqualTo(testAccount);
    }

    @Test
    public void updateBalance() {
        BankAccount expected = new BankAccount("NL13DIGO6940102114", 99.99);
        BankAccount actual  = bankAccountDaoTest.updateBalance(expected);
        System.out.println(expected);
        System.out.println(actual);
        assertThat(actual).isEqualTo(expected);
    }

}
