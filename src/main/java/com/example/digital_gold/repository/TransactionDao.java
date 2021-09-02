package com.example.digital_gold.repository;

import com.example.digital_gold.domain.Transaction;
import java.util.List;

public interface TransactionDao {

    Transaction saveTransaction(Transaction transaction);
    List<Transaction> findTransactionsByIban(String iban);


}
