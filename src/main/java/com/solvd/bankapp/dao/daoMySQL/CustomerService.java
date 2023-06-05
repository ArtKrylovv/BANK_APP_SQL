package com.solvd.bankapp.dao.daoMySQL;

import com.solvd.bankapp.dao.iCustomerDao;
import com.solvd.bankapp.dao.iSavingsDao;
import com.solvd.bankapp.model.Address;
import com.solvd.bankapp.model.Customer;
import com.solvd.bankapp.model.SavingsAccount;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerService {
    private iCustomerDao customerDao = new CustomerDaoImpl();
    public iSavingsDao savingDao = new SavingsDaoImpl();

    public Customer get(int ssn) throws SQLException {
        Customer customer = null;
        customer = customerDao.get(ssn);
        List<SavingsAccount> listSavingsAccounts = savingDao.getSavingsAccountsListBySsn(ssn);
        customer.setSavingAccountsList(listSavingsAccounts);
        return customer;
    }

    public List<Customer> getAll() throws SQLException {
        List<Customer> customersList = new ArrayList<>();
        for (Customer customer : customerDao.getAll()) {
            List<SavingsAccount> listSavingsAccounts = savingDao.getSavingsAccountsListBySsn(customer.getSsn());
            customer.setSavingAccountsList(listSavingsAccounts);
            customersList.add(customer);
        }
        return customersList;
    }
}