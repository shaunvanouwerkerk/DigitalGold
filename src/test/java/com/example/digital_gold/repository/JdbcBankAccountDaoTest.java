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


    @Test
    public void saveBankAccount() {
        BankAccount  testSave = new BankAccount("NL13DIGO6940102114", 93580.35);
        BankAccount actualAccount = bankAccountDaoTest.saveBankAccount(testSave);
        assertThat(actualAccount).isEqualTo(testSave);
    }

    @Test
    public void updateBalance() {
        BankAccount testUpdateBalance = new BankAccount("NL39DIGO4296384680", 3840.26);
        bankAccountDaoTest.saveBankAccount(testUpdateBalance);
        BankAccount expected = new BankAccount("NL39DIGO4296384680", 99.99);
        BankAccount actual  = bankAccountDaoTest.updateBalance(expected);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void getBalanceByIban() {
        BankAccount  testBalance = new BankAccount("NL96DIGO3074958287", 92880.25);
        bankAccountDaoTest.saveBankAccount(testBalance);
        double expected = 92880.25;
        double actual = bankAccountDaoTest.getBalanceByIban("NL96DIGO3074958287");
        assertThat(actual).isEqualTo(expected);

    }
}
