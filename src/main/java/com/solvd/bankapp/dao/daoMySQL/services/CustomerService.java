package com.solvd.bankapp.dao.daoMySQL.services;

import com.solvd.bankapp.dao.*;
import com.solvd.bankapp.dao.daoMySQL.impl.*;
import com.solvd.bankapp.model.*;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerService {
    private static final Logger LOGGER = LogManager.getLogger(CustomerService.class);
    private iCustomerDao customerDao = new CustomerDaoImpl();
    private iSavingsDao savingDao = new SavingsDaoImpl();
    private iCreditCardDao creditCardDao = new CreditCardDaoImpl();
    private iCheckingDao checkingDao = new CheckingDaoImpl();
    private iAddressDao addressDao = new AddressDaoImpl();

    public Customer get(int ssn, boolean full) throws SQLException {
        if (ssn <= 0) {
            LOGGER.error("invalid id argument");
            throw new IllegalArgumentException("Id must be int >=1");
        }
        Customer customer = null;
            if (full) {
                customer = customerDao.get(ssn);
                List<SavingsAccount> listSavingsAccounts = savingDao.getSavingsAccountsListBySsn(ssn);
                List<CreditCardAccount> listCreditCardsAccounts = creditCardDao.getCreditCardAccountsListBySsn(ssn);
                CheckingAccount checkingAccount = checkingDao.getCheckingAccountBySsn(ssn);
                Address address = addressDao.get(customerDao.getAddressIdBySsn(customer.getSsn()));
                customer.setSavingAccountsList(listSavingsAccounts);
                customer.setCreditCardAccounts(listCreditCardsAccounts);
                customer.setCheckingAccount(checkingAccount);
                customer.setAddress(address);
            } else {
                customer = customerDao.get(ssn);
            }
            return customer;
    }

    public List<Customer> getAll(boolean full) throws SQLException {
        List<Customer> customersList = new ArrayList<>();
        if(full) {
            for (Customer customer : customerDao.getAll()) {
                List<SavingsAccount> listSavingsAccounts = savingDao.getSavingsAccountsListBySsn(customer.getSsn());
                List<CreditCardAccount> listCreditCardsAccounts = creditCardDao.getCreditCardAccountsListBySsn(customer.getSsn());
                CheckingAccount checkingAccount = checkingDao.getCheckingAccountBySsn(customer.getSsn());
                Address address = addressDao.get(customerDao.getAddressIdBySsn(customer.getSsn()));
                customer.setSavingAccountsList(listSavingsAccounts);
                customer.setCreditCardAccounts(listCreditCardsAccounts);
                customer.setCheckingAccount(checkingAccount);
                customer.setAddress(address);
                customersList.add(customer);
            }
        } else {
            customersList = customerDao.getAll();
        }
        return customersList;
    }

    public int create(Customer customer) throws SQLException {
        return customerDao.create(customer);
    }

    public int update(Customer customer) throws SQLException {
        return customerDao.update(customer);
    }

    public int delete(int ssn) throws SQLException {
        return customerDao.delete(ssn);
    }
}