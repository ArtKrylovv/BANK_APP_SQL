package com.solvd.bankapp.mappers;

import com.solvd.bankapp.bin.CheckingAccount;
import com.solvd.bankapp.bin.Customer;

import java.util.List;

public interface CustomerMapper {
    Customer selectCustomerById(int id);
    Customer selectCustomerByIdFull(int id);
    List<Customer> selectAll();
    List<Customer> selectAllFull();
    void createCustomer(Customer customer);
    void updateCustomer(Customer customer);
    void deleteCustomer(int id);

}
