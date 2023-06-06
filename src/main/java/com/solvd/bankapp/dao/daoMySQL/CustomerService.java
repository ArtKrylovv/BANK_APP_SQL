package com.solvd.bankapp.dao.daoMySQL;

import com.solvd.bankapp.dao.iCheckingDao;
import com.solvd.bankapp.dao.iCreditCardDao;
import com.solvd.bankapp.dao.iCustomerDao;
import com.solvd.bankapp.dao.iSavingsDao;
import com.solvd.bankapp.model.*;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerService {
    private iCustomerDao customerDao = new CustomerDaoImpl();
    private iSavingsDao savingDao = new SavingsDaoImpl();
    private iCreditCardDao creditCardDao = new CreditCardDaoImpl();
    private iCheckingDao checkingDao = new CheckingDaoImpl();

    public Customer get(int ssn) throws SQLException {
        Customer customer = null;
        customer = customerDao.get(ssn);
        List<SavingsAccount> listSavingsAccounts = savingDao.getSavingsAccountsListBySsn(ssn);
        List<CreditCardAccount> listCreditCardsAccounts = creditCardDao.getCreditCardAccountsListBySsn(ssn);
        CheckingAccount checkingAccount = checkingDao.getCheckingAccountBySsn(ssn);
        customer.setSavingAccountsList(listSavingsAccounts);
        customer.setCreditCardAccounts(listCreditCardsAccounts);
        customer.setCheckingAccount(checkingAccount);
        return customer;
    }

    public List<Customer> getAll() throws SQLException {
        List<Customer> customersList = new ArrayList<>();
        for (Customer customer : customerDao.getAll()) {
            List<SavingsAccount> listSavingsAccounts = savingDao.getSavingsAccountsListBySsn(customer.getSsn());
            List<CreditCardAccount> listCreditCardsAccounts = creditCardDao.getCreditCardAccountsListBySsn(customer.getSsn());
            CheckingAccount checkingAccount = checkingDao.getCheckingAccountBySsn(customer.getSsn());
            customer.setSavingAccountsList(listSavingsAccounts);
            customer.setCreditCardAccounts(listCreditCardsAccounts);
            customer.setCheckingAccount(checkingAccount);
            customersList.add(customer);
        }
        return customersList;
    }
}