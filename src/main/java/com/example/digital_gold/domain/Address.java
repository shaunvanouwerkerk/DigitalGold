package com.example.digital_gold.domain;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

/*
@Author Jany Gaal
*/

public class Address {

    private int houseNumber;
    @NotBlank(message = "verplicht")
    private String streetName;
    @NotBlank(message = "verplicht")
    private String zipCode;
    @NotBlank(message = "verplicht")
    private String city;

    public Address(int houseNumber, String streetName, String zipCode, String city) {
        this.houseNumber = houseNumber;
        this.streetName = streetName;
        this.zipCode = zipCode;
        this.city = city;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return houseNumber == address.houseNumber && streetName.equals(address.streetName) && zipCode.equals(address.zipCode) && city.equals(address.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(houseNumber, streetName, zipCode, city);
    }

    @Override
    public String toString() {
        return "Address{" +
                "houseNumber=" + houseNumber +
                ", streetName='" + streetName + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}


