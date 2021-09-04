package com.example.digital_gold.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

/*
@Author Jany Gaal
*/

@Entity
public abstract class Account {

    @Id
    @NotBlank(message = "Gebruikernaam is verplicht")
    protected String username;
    @NotBlank(message = " Wachtwoord is verplicht")
    @Size(min = 8, max = 200, message
            = "Wachtwoord moet tussen de 8 en 200 tekens zijn")
    protected String password;
    protected String salt;
    protected boolean status;

    public Account(){};

    public Account(String username, String password, String salt, boolean status) {
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.status = status;
    }

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
        this.salt = null;
        this.status = true;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public boolean isStatus() { return status; }

    public void setStatus(boolean status) { this.status = status; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return status == account.status && username.equals(account.username) && password.equals(account.password) && salt.equals(account.salt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, salt, status);
    }

    @Override
    public String toString() {
        return "Account{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", status=" + status +
                '}';
    }
}
