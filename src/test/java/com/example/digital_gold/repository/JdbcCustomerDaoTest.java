package com.example.digital_gold.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@ActiveProfiles ("test")
public class JdbcCustomerDaoTest {

    private JdbcCustomerDao jdbcCustomerDaoTest;

    @Autowired
    public JdbcCustomerDaoTest(JdbcCustomerDao dao) {
        this.jdbcCustomerDaoTest = dao;
    }

    @Test
    public void customerDaoNutNull() {
        assertThat(jdbcCustomerDaoTest).isNotNull();
    }
    @Test
    void save() {
    }

    @Test
    void findCustomerByUsername() {
    }
}