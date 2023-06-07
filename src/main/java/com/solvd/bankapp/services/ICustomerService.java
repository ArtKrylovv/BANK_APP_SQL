package com.solvd.bankapp.services;

import com.solvd.bankapp.bin.Customer;

import java.util.List;

public interface ICustomerService extends IService <Customer> {
    Customer readFromDb(int ssn, boolean full);
    public List<Customer> readAllFromDb(boolean full);
}
