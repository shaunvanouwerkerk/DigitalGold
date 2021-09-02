package com.example.digital_gold.repository;

import com.example.digital_gold.domain.Administrator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles ("test")
class JdbcAdministatorDaoTest {

    private JdbcAdministatorDao jdbcAdministatorDao;

    @Autowired
    public JdbcAdministatorDaoTest(JdbcAdministatorDao jdbcAdministatorDao) {
        this.jdbcAdministatorDao = jdbcAdministatorDao;
    }

    @Test
    void save() {
        Administrator administrator1 = new Administrator("AdministratorTest","Testpassword");
        Administrator actualAdministrator = jdbcAdministatorDao.save(administrator1);
        assertThat(actualAdministrator).isEqualTo(administrator1);

    }
}