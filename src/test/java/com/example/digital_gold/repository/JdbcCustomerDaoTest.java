package com.example.digital_gold.repository;

import com.example.digital_gold.domain.Address;
import com.example.digital_gold.domain.Customer;
import com.example.digital_gold.domain.CustomerDetails;
import com.example.digital_gold.domain.FullName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.sql.Date;

import static org.assertj.core.api.Assertions.assertThat;

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
    public void save() {
        FullName testfullname = new FullName("Tester", "van", "Tester");
        Address testadress = new Address(1, "TestStraat", "1111AA", "TestCity");
        CustomerDetails testcustomerDetails = new CustomerDetails(Date.valueOf("1900-01-01"),"753654852",
                "tester@gmail.com" );
        Customer testcustomer = new Customer("TestUser02", "TestPassword", "zoutje", testfullname,
                testadress, testcustomerDetails);
        Customer actualcustomer = jdbcCustomerDaoTest.save(testcustomer);
        assertThat(actualcustomer).isEqualTo(testcustomer);
    }

    @Test
    public void findCustomerByUsernameTrue() {
        Boolean actual = jdbcCustomerDaoTest.findCustomerByUsername("TestUser02");
        Boolean expected = true;
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void findCustomerByUsernameFalse() {
        Boolean actual = jdbcCustomerDaoTest.findCustomerByUsername("TestUser03");
        Boolean expected = false;
        assertThat(actual).isEqualTo(expected);
    }
}