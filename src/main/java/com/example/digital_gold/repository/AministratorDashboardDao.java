package com.example.digital_gold.repository;
import com.example.digital_gold.domain.Administrator;
import com.example.digital_gold.domain.AdministratorDashboard;
import com.example.digital_gold.domain.BankAccount;

/**
 * @Aythor Shaun van Ouwerkerk
 */


public interface AministratorDashboardDao {

    AdministratorDashboard save(AdministratorDashboard administratorDashboard);

    Double findStartingBudgetByUsername(String username);

    Double findTransactionFeeByUsername(String username);

    AdministratorDashboard updateStartingBudget (AdministratorDashboard administratorDashboard);

    AdministratorDashboard updateTransactionFee (AdministratorDashboard administratorDashboard);


}

