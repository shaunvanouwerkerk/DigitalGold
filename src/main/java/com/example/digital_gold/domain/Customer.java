package com.example.digital_gold.domain;

//TODO constructor met salt default null Jany
//TODO objecten aanmaken Fiona (fullname) / Sandra (details) /Jany (address)
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;

public class Customer extends Account {
    private final Logger logger = LoggerFactory.getLogger(Customer.class);

    private String firstName; //FullName
    private String prefix;
    private String lastName;

    private Date dateOfBirth; //CustomerDetails
    private int bsn;
    private String emailaddress;

    private int houseNumber; //adress
    private String streetName;
    private String zipCode;
    private String city;



    public Customer(String username, String password, String firstName, String prefix, String lastName, Date dateOfBirth,
                    int bsn, int houseNumber, String streetName, String zipCode, String city) {
        super(username, password);
        this.firstName = firstName;
        this.prefix = prefix;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.bsn = bsn;
        this.houseNumber = houseNumber;
        this.streetName = streetName;
        this.zipCode = zipCode;
        this.city = city;
        logger.info("New Customer");
    }

    public String getFirstName() {
        return firstName;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public int getBsn() {
        return bsn;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getCity() {
        return city;
    }


    @Override
    public String toString() {
        return "Customer{" +
                "firstName='" + firstName + '\'' +
                ", prefix='" + prefix + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", bsn=" + bsn +
                ", houseNumber=" + houseNumber +
                ", streetName='" + streetName + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}

