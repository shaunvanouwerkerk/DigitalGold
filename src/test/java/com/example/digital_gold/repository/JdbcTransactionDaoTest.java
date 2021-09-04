package com.example.digital_gold.repository;

import com.example.digital_gold.domain.Transaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author David Truijens
 */


@SpringBootTest
@ActiveProfiles("test")
class JdbcTransactionDaoTest {

    private TransactionDao transactionDaoTest;

    @Autowired
    public JdbcTransactionDaoTest(TransactionDao transactionDaoTest) {
        this.transactionDaoTest = transactionDaoTest;
    }

    @Test
    public void transactionDaoTestNotNull() {
        assertThat(transactionDaoTest).isNotNull();
    }

    @Test //TODO: check welk datatype we nodig hebben. LocalDate of LocalDateTime. Nu een conflict met de database.
    void saveTransaction() {
        Transaction testTransaction = new Transaction(LocalDateTime.now(),"BTC",0.233,
                102.67,0.01,"NL64DIGO0001734105","NL52DEUT0009374258");
        Transaction transaction = transactionDaoTest.saveTransaction(testTransaction);
        assertThat(transaction).isEqualTo(testTransaction);
    }

    @Test
    void findTransactionsByIban() {
        String iban = "NL24DIGO2222222222";
        List<Transaction> foundTransactions = new ArrayList<Transaction>();
        foundTransactions = transactionDaoTest.findTransactionsByIban(iban);
        for (Transaction transaction:foundTransactions) {
            System.out.println(transaction);
        }
        assertThat(foundTransactions.size()).isEqualTo(3);
        assertThat(foundTransactions.get(0).getTransactionId()).isEqualTo(1);
        assertThat(foundTransactions.get(1).getTransactionId()).isEqualTo(2);
        assertThat(foundTransactions.get(2).getTransactionId()).isEqualTo(3);
        assertThat(foundTransactions.get(0).getIbanBuy()).isEqualTo(iban);
        assertThat(foundTransactions.get(1).getIbanSell()).isEqualTo(iban);
        assertThat(foundTransactions.get(2).getIbanSell()).isEqualTo(iban);
    }
}