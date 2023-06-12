package com.solvd.bankapp.bin;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class CheckingAccount {
    @XmlAttribute
    private long accountNumber;
    @XmlElement(name="balance")
    private long balance;
    @XmlTransient
    private int customerId;

    public CheckingAccount(long accountNumber,long balance, int customerId) {
        this.accountNumber =  accountNumber;
        this.balance = balance;
        this.customerId = customerId;
    }

    public CheckingAccount() {
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public long getBalance() {
        return balance;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "CheckingAccount{" +
                "accountNumber=" + accountNumber +
                ", balance=" + balance +
                ", customerId=" + customerId +
                '}';
    }
}
