package com.solvd.bankapp.services.implMySQL;

import com.solvd.bankapp.dao.*;
import com.solvd.bankapp.dao.impl.*;
import com.solvd.bankapp.bin.*;
import com.solvd.bankapp.services.mysql.ICustomerService;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.ArrayList;
import java.util.List;

public class CustomerService implements ICustomerService {
    private static final Logger LOGGER = LogManager.getLogger(CustomerService.class);
    private ICustomerDao customerDao = new CustomerDaoImpl();
    private ISavingsDao savingDao = new SavingsDaoImpl();
    private ICreditCardDao creditCardDao = new CreditCardDaoImpl();
    private ICheckingDao checkingDao = new CheckingDaoImpl();
    private IAddressDao addressDao = new AddressDaoImpl();

    @Override
    public Customer readFromDb(int id) {
        throw new UnsupportedOperationException("use overloaded method with boolean type");
    }

    @Override
    public List<Customer> readAllFromDb() {
        throw new UnsupportedOperationException("use overloaded method with boolean type");
    }

    public Customer readFromDb(int ssn, boolean full)  {
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

    public List<Customer> readAllFromDb(boolean full){
        List<Customer> customersList = new ArrayList<>();
        if(full) {
            List<Customer> finalCustomersList = customersList;
            customerDao.getAll().forEach(customer ->  {
                List<SavingsAccount> listSavingsAccounts = savingDao.getSavingsAccountsListBySsn(customer.getSsn());
                List<CreditCardAccount> listCreditCardsAccounts = creditCardDao.getCreditCardAccountsListBySsn(customer.getSsn());
                CheckingAccount checkingAccount = checkingDao.getCheckingAccountBySsn(customer.getSsn());
                Address address = addressDao.get(customerDao.getAddressIdBySsn(customer.getSsn()));
                customer.setSavingAccountsList(listSavingsAccounts);
                customer.setCreditCardAccounts(listCreditCardsAccounts);
                customer.setCheckingAccount(checkingAccount);
                customer.setAddress(address);
                finalCustomersList.add(customer);
            });
        } else {
            customersList = customerDao.getAll();
        }
        return customersList;
    }

    public int writeToDb(Customer customer)  {
        if (customer != null) {
            return customerDao.create(customer);
        } else {
            LOGGER.error("invalid argument");
            throw new NullPointerException();
        }
    }

    public int updateInDb(Customer customer) {
        if (customer != null) {
            return customerDao.update(customer);
        } else {
            LOGGER.error("invalid argument");
            throw new NullPointerException();
        }
    }
    public int removeFromDb(int ssn) {
        if (ssn <= 0) {
            LOGGER.error("invalid id argument");
            throw new IllegalArgumentException("Id must be int >=1");
        }
        return customerDao.delete(ssn);
    }
}