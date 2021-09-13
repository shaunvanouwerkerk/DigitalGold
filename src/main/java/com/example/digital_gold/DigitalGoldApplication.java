package com.example.digital_gold;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DigitalGoldApplication {

    public static void main(String[] args) {
        SpringApplication.run(DigitalGoldApplication.class, args);
    }

}
