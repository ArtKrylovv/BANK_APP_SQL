package com.solvd.bankapp.model;

public class CheckingAccount {
    private long accountNumber;
    private long balance;
    private int customerId;

    public CheckingAccount(long balance, int customerId) {
        this.balance = balance;
        this.customerId = customerId;
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
}
