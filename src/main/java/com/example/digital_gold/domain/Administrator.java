package com.example.digital_gold.domain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author Shaun van Ouwerkerk
 */

public class Administrator extends Account{
    private final Logger logger = LoggerFactory.getLogger(Administrator.class);


    public Administrator(String username, String password, String salt) {
        super(username, password, salt);
    }

    public Administrator(String username, String password) {
        super(username, password);
    }

    public Administrator() {
    }

    @Override
    public String toString() {
        return "Administrator{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                '}';
    }
}
