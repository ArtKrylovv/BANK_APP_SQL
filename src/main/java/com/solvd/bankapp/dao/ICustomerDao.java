package com.solvd.bankapp.dao;

import com.solvd.bankapp.bin.Customer;

import java.sql.SQLException;

public interface ICustomerDao extends IDao<Customer> {
    int getAddressIdBySsn(int ssn);
}
