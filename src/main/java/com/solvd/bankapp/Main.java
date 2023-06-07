package com.solvd.bankapp;

import com.solvd.bankapp.dao.daoMySQL.impl.*;
import com.solvd.bankapp.dao.daoMySQL.services.*;
import com.solvd.bankapp.model.*;
import com.solvd.bankapp.utils.ConnectionPool;

import javax.sound.midi.Soundbank;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;

public class Main {
    //TODO implement flags to access short/full objects, finalize CRUD, implement Customer relations table, add logger
    public static void main (String[] args) throws SQLException {

        // create address
//        StateDaoImpl stateDao = new StateDaoImpl();
//        AddressDaoImpl addressDao = new AddressDaoImpl();
//        Address address = new Address(11, 100, "Lincoln blvd", 1,
//                "Caramel",stateDao.get(1));
//        addressDao.create(address);
//
//        // create customer
//        CheckingAccount checkingAccount = null;
//        List<SavingsAccount> savingsAccountList = new ArrayList<>();
//        List<CreditCardAccount> creditCardAccounts = new ArrayList<>();
//        CustomerDaoImpl customerDao = new CustomerDaoImpl();
//        customerDao.create(new Customer(777777777,"Alan", "Shepard",
//                address, savingsAccountList, creditCardAccounts, checkingAccount));
//
//        // create checking account
//        CheckingDaoImpl checkingDao = new CheckingDaoImpl();
//        checkingDao.create(new CheckingAccount(0, 777, 777777777));
//
//        // create savings account
//        SavingsDaoImpl savingsDao = new SavingsDaoImpl();
//        System.out.println(savingsDao.create(new SavingsAccount(0L, 0.07, 777, 777777777)));
//        System.out.println(savingsDao.create(new SavingsAccount(0L, 0.07, 888, 777777777)));
//
//        // create savings account
//        CreditCardDaoImpl creditCardDao = new CreditCardDaoImpl();
//        System.out.println(creditCardDao.create(new CreditCardAccount(0L, 0.17, 1000, 777777777)));
//        System.out.println(creditCardDao.create(new CreditCardAccount(0L, 0.17, 2000, 777777777)));
//
//        // query created customer full info
//        System.out.println(new CustomerService().get(777777777));

//        this.accountNumber = accountNumber;
//        this.interest = interest;
//        this.balance = balance;
//        this.customerId = customerId;

//        this.ssn = ssn;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.address = address;
//        this.savingAccountsList = savingAccountsList;
//        this.creditCardAccounts = creditCardAccounts;
//        this.checkingAccount = checkingAccount;

        AddressService addressService = new AddressService();
        Address address = new Address(777, 100, "test",0, "Test", new State(1,"TE", "Test"));
//        System.out.println(addressService.create(address));
        CheckingAccount checkingAccount = new CheckingAccount();
        List<SavingsAccount> savingsAccountList = new ArrayList<>();
        List<CreditCardAccount> creditCardAccountList = new ArrayList<>();
//        System.out.println(new CustomerService().create(new Customer(555555555, "Ivan", "Ivanov",
//                address, savingsAccountList,creditCardAccountList, checkingAccount)));

        System.out.println(new CustomerService().delete(555555555));
        System.out.println(new CustomerService().getAll(false));

    }
}
