package com.example.digital_gold.repository;
import com.example.digital_gold.domain.Administrator;

/**
 * @Aythor Shaun van Ouwerkerk
 */


public interface AdministratorDao {

    Administrator save(Administrator administrator);

    String findAdministratorSalt(String username);

    String findAdministratorHashPassword(String username);

    boolean findAdministratorByUsername(String username);
}

