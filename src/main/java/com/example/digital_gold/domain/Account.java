package com.example.digital_gold.domain;

//TODO AANPASSEN SALT toevoeggen en setters SALT MOET DEFAULT NULL Jany
public abstract class Account {

    protected String username;
    protected String password;

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }


}
