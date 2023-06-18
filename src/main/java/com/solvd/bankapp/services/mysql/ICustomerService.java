package com.solvd.bankapp.services.mysql;

import com.solvd.bankapp.bin.Customer;
import com.solvd.bankapp.services.IService;

import java.util.List;

public interface ICustomerService extends IService<Customer> {
    Customer readFromDb(int ssn, boolean full);
    public List<Customer> readAllFromDb(boolean full);
}
