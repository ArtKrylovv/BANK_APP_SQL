package com.solvd.bankapp.bin;
import com.solvd.bankapp.handlers.DateAdapter;
import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.sql.Date;


@XmlAccessorType(XmlAccessType.FIELD)
public class CheckingAccount {
    @XmlAttribute
    private long accountNumber;
    @XmlElement(name="balance")
    private long balance;
    @XmlTransient
    private int customerId;
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date dateCreated;

    public CheckingAccount(long accountNumber,long balance, int customerId, Date dateCreated) {
        this.accountNumber =  accountNumber;
        this.balance = balance;
        this.customerId = customerId;
        this.dateCreated = dateCreated;
    }

    public CheckingAccount() {
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public Date getDateCreated() {
        return dateCreated;
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

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public String toString() {
        return "CheckingAccount{" +
                "accountNumber=" + accountNumber +
                ", balance=" + balance +
                ", customerId=" + customerId +
                ", date=" + dateCreated +
                '}';
    }
}
