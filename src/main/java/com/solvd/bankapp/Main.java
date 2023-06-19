package com.solvd.bankapp;

import com.solvd.bankapp.bin.Address;
import com.solvd.bankapp.bin.Customer;
import com.solvd.bankapp.services.implMySQl.CustomerService;
import com.solvd.bankapp.services.mysql.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class  Main {
    public static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args)  {
        // mybatis / mysql
        ICustomerService customerService = new CustomerService();
        LOGGER.info(customerService.writeToDb(new Customer(22222222, "Art", "Krylov", new Address(1), null, null, null)));
        LOGGER.info(customerService.updateInDb(new Customer(22222222, "Arti", "Krylov", new Address(1), null, null, null)));
        LOGGER.info(customerService.removeFromDb(22222222));
        LOGGER.info(customerService.readFromDb(222222222, false));
        LOGGER.info(customerService.readAllFromDb(false));
        LOGGER.info(customerService.readAllFromDb(true));
    }
}

