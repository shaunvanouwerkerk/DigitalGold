package com.example.digital_gold.domain;

import java.sql.Date;
// @author Sandra Turina

public class CustomerDetails {

    private Date dateOfBirth;
    private String bsn;
    private String emailaddress;

    public CustomerDetails(Date dateOfBirth, String bsn, String emailaddress) {
        this.dateOfBirth = dateOfBirth;
        this.bsn = bsn;
        this.emailaddress = emailaddress;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getBsn() {
        return bsn;
    }

    public void setBsn(String bsn) {
        this.bsn = bsn;
    }

    public String getEmailaddress() {
        return emailaddress;
    }

    public void setEmailaddress(String emailaddress) {
        this.emailaddress = emailaddress;
    }

    @Override
    public String toString() {
        return "CustomerDetails{" +
                "dateOfBirth=" + dateOfBirth +
                ", bsn=" + bsn +
                ", emailaddress='" + emailaddress + '\'' +
                '}';
    }
}
