package com.example.digital_gold.domain;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

/**
*  @author Fiona Gray
* */
public class FullName {

    @NotBlank(message = "verplicht")
    private String firstName;
    private String prefix;
    @NotBlank(message = "verplicht")
    private String lastName;

    public FullName(String firstName, String prefix, String lastName) {
        this.firstName = firstName;
        this.prefix = prefix;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FullName fullName = (FullName) o;
        return firstName.equals(fullName.firstName) && Objects.equals(prefix, fullName.prefix) && lastName.equals(fullName.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, prefix, lastName);
    }

    @Override
    public String toString() {
        return "FullName{" +
                "firstName='" + firstName + '\'' +
                ", prefix='" + prefix + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

}
