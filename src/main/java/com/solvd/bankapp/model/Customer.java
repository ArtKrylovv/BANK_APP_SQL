package com.solvd.bankapp.model;

import java.util.List;

public class Customer {
    private int ssn;
    private String firstName;
    private String lastName;
    private Address address;
    private List<SavingsAccount> savingAccountsList;

//  to be implemented
//    private List<CreditCardAccount> creditCardAccountsList;
//    private CheckingAccount checkingAccount;


    public Customer(int ssn, String firstName, String lastName, Address address, List<SavingsAccount> savingAccountsList) {
        this.ssn = ssn;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.savingAccountsList = savingAccountsList;
    }

    public Customer() {
    }

    public int getSsn() {
        return ssn;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Address getAddress() {
        return address;
    }

    public List<SavingsAccount> getSavingAccountsList() {
        return savingAccountsList;
    }

    public void setSsn(int ssn) {
        this.ssn = ssn;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setSavingAccountsList(List<SavingsAccount> savingAccountsList) {
        this.savingAccountsList = savingAccountsList;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "ssn=" + ssn +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address=" + address +
                ", savingAccountsList=" + savingAccountsList +
                '}';
    }
}
