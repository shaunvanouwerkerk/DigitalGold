//package com.example.digital_gold.configuration;
///**
// * @Author Shaun van Ouwerkerk
// */
//
//import com.example.digital_gold.repository.RootRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//
//@Configuration
//public class Config {
//    @Value("${usernameDGB}")
//    public String usernameBank;
//    public double startingBudget;
//    public double transactionFee;
//    @Autowired
//    RootRepository rootRepository;
//
//
//    public void setStartingBudget(double startingBudget) {
//        this.startingBudget = startingBudget;
//    }
//
//    @Bean
//    public double getStartingBudget() {
//        startingBudget = rootRepository.findStartingBudgetByUsername(usernameBank);
//        return startingBudget;
//    }
//
//    public double getTransactionFee() {
//        return transactionFee;
//    }
//}
//
//
//
