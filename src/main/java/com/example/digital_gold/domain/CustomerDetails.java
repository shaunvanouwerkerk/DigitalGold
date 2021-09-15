package com.example.digital_gold.domain;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.Objects;
// @author Sandra Turina

public class CustomerDetails {

    private Date dateOfBirth;
    @NotBlank(message = "verplicht")
    @Size(min = 9, max = 9, message
            = "Ongeldig BSN")
    private String bsn;
    private String iban;
    @NotBlank(message = "verplicht")
    @Email
    private String emailaddress;

    public CustomerDetails(Date dateOfBirth, String bsn, String emailaddress, String iban) {
        this.dateOfBirth = dateOfBirth;
        this.bsn = bsn;
        this.emailaddress = emailaddress;
        this.iban = iban;
    }

    public CustomerDetails() {
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

    public String getIban() { return iban; }

    public void setIban(String iban) { this.iban = iban; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerDetails that = (CustomerDetails) o;
        return dateOfBirth.equals(that.dateOfBirth) && bsn.equals(that.bsn) && iban.equals(that.iban) && emailaddress.equals(that.emailaddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateOfBirth, bsn, iban, emailaddress);
    }

    @Override
    public String toString() {
        return "CustomerDetails{" +
                "dateOfBirth=" + dateOfBirth +
                ", bsn='" + bsn + '\'' +
                ", iban='" + iban + '\'' +
                ", emailaddress='" + emailaddress + '\'' +
                '}';
    }
}
