package com.solvd.bankapp.bin;

import jakarta.xml.bind.annotation.*;

import java.util.List;
@XmlRootElement(name="customer")
@XmlAccessorType(XmlAccessType.FIELD)
public class Customer {
    @XmlAttribute
    private int ssn;
    @XmlElement(name="firstName")
    private String firstName;
    @XmlElement(name="lastName")
    private String lastName;
    @XmlElement(name="address")
    private Address address;
    @XmlElementWrapper(name="savingsAccounts")
    @XmlElement(name="account")
    private List<SavingsAccount> savingAccountsList;
    @XmlElementWrapper(name="creditCardAccounts")
    @XmlElement(name="account")
    private List<CreditCardAccount> creditCardAccounts;
    @XmlElement(name="checkingAccount")
    private CheckingAccount checkingAccount;

    public Customer(int ssn, String firstName, String lastName, Address address,
                    List<SavingsAccount> savingAccountsList, List<CreditCardAccount> creditCardAccounts, CheckingAccount checkingAccount) {
        this.ssn = ssn;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.savingAccountsList = savingAccountsList;
        this.creditCardAccounts = creditCardAccounts;
        this.checkingAccount = checkingAccount;
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

    public List<CreditCardAccount> getCreditCardAccounts() {
        return creditCardAccounts;
    }

    public CheckingAccount getCheckingAccount() {
        return checkingAccount;
    }

    public void setCreditCardAccounts(List<CreditCardAccount> creditCardAccounts) {
        this.creditCardAccounts = creditCardAccounts;
    }

    public void setCheckingAccount(CheckingAccount checkingAccount) {
        this.checkingAccount = checkingAccount;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "ssn=" + ssn +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address=" + address +
                ", savingAccountsList=" + savingAccountsList +
                ", creditCardAccounts=" + creditCardAccounts +
                ", checkingAccount=" + checkingAccount +
                '}';
    }
}
