package com.solvd.bankapp.mappers;

import com.solvd.bankapp.bin.CheckingAccount;
import com.solvd.bankapp.bin.Customer;

import java.util.List;

public interface CustomerMapper {
    Customer selectCustomerById(int id);
    List<Customer> selectCustomerAll();
    void createCustomer(Customer customer);
    void updateCustomer(Customer customer);
    void deleteCustomer(int id);

}
