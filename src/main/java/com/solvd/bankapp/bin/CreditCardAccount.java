package com.solvd.bankapp.bin;

public class CreditCardAccount {
    private long accountNumber;
    private double interest;
    private long balance;
    private int customerId;

    public CreditCardAccount(long accountNumber, double interest, long balance,  int customerId) {
        this.accountNumber =accountNumber;
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

    @Override
    public String toString() {
        return "CreditCardAccount{" +
                "accountNumber=" + accountNumber +
                ", interest=" + interest +
                ", balance=" + balance +
                ", customerId=" + customerId +
                '}';
    }
}