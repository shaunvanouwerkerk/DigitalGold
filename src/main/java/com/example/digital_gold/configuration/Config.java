package com.example.digital_gold.configuration;
/**
 * @Author Shaun van Ouwerkerk
 */

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class Config {
    @Value("${startingbudget}")
    public double startingBudget;
    public double transactionFee;



    public void setStartingBudget(double startingBudget) {
        this.startingBudget = startingBudget;
    }

    @Bean
    public double getStartingBudget() {
        return startingBudget;
    }

    public double getTransactionFee() {
        return transactionFee;
    }
}



