package com.solvd.bankapp;

import com.solvd.bankapp.services.IAddressService;
import com.solvd.bankapp.services.ICustomerService;
import com.solvd.bankapp.services.impl.AddressService;
import com.solvd.bankapp.services.impl.CustomerService;

public class Test {
    public static void main(String[] args) {
        IAddressService addressService = new AddressService();
        System.out.println(addressService.readAllFromDb(true));

        ICustomerService customerService = new CustomerService();
        System.out.println(customerService.readAllFromDb(true));
    }
}
