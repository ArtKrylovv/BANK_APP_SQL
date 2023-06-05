package com.solvd.bankapp.model;

public class CreditCardAccount {
    private long accountNumber;
    private double interest;
    private long balance;
    private int customerId;

    public CreditCardAccount(double interest, long balance, int customerId) {
        this.interest = interest;
        this.balance = balance;
        this.customerId = customerId;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public double getInterest() {
        return interest;
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

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}
