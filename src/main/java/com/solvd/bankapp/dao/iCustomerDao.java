package com.solvd.bankapp.dao;

import com.solvd.bankapp.model.Customer;

import java.sql.SQLException;

public interface iCustomerDao extends iDao<Customer> {
    int getAddressIdBySsn(int ssn) throws SQLException;
}
