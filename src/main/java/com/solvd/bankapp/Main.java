package com.solvd.bankapp;

import com.solvd.bankapp.dao.daoMySQL.impl.*;
import com.solvd.bankapp.dao.daoMySQL.services.CustomerService;
import com.solvd.bankapp.dao.daoMySQL.services.LoanApplicationService;
import com.solvd.bankapp.model.*;
import com.solvd.bankapp.utils.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    //TODO implement flags to access short/full objects, finalize CRUD, implement Customer relations table,
    // check how to resolve address id issue
    public static void main (String[] args) throws SQLException {

        // create address
        StateDaoImpl stateDao = new StateDaoImpl();
        AddressDaoImpl addressDao = new AddressDaoImpl();
        Address address = new Address(0, 100, "Lincoln blvd", 1,
                "Caramel",stateDao.get(1));
        addressDao.create(address);

        // create customer
        CheckingAccount checkingAccount = null;
        List<SavingsAccount> savingsAccountList = new ArrayList<>();
        List<CreditCardAccount> creditCardAccounts = new ArrayList<>();
        address.setId(11);
        CustomerDaoImpl customerDao = new CustomerDaoImpl();
        customerDao.create(new Customer(777777777,"Alan", "Shepard",
                address, savingsAccountList, creditCardAccounts, checkingAccount));

        // create checking account
        CheckingDaoImpl checkingDao = new CheckingDaoImpl();
        checkingDao.create(new CheckingAccount(0, 777, 777777777));

        // create savings account
        SavingsDaoImpl savingsDao = new SavingsDaoImpl();
        System.out.println(savingsDao.create(new SavingsAccount(0L, 0.07, 777, 777777777)));
        System.out.println(savingsDao.create(new SavingsAccount(0L, 0.07, 888, 777777777)));

        // create savings account
        CreditCardDaoImpl creditCardDao = new CreditCardDaoImpl();
        System.out.println(creditCardDao.create(new CreditCardAccount(0L, 0.17, 1000, 777777777)));
        System.out.println(creditCardDao.create(new CreditCardAccount(0L, 0.17, 2000, 777777777)));

        // query created customer full info
        System.out.println(new CustomerService().get(777777777));

    }
}
