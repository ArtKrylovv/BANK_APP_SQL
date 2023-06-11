package com.solvd.bankapp;

import com.solvd.bankapp.bin.*;
import com.solvd.bankapp.services.*;
import com.solvd.bankapp.services.impl.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main (String[] args) {

        // creates new address
        Address address = new Address(100, 100, "test",0, "Test", new State(1,"TE", "Test"));
        IAddressService addressService = new AddressService();
        addressService.writeToDb(address);

        // create new customer
        CheckingAccount checkingAccount = new CheckingAccount();
        List<SavingsAccount> savingsAccountList = new ArrayList<>();
        List<CreditCardAccount> creditCardAccountList = new ArrayList<>();

        ICustomerService customerService = new CustomerService();
        customerService.writeToDb(new Customer(555555555, "John", "Doe", address, savingsAccountList,creditCardAccountList, checkingAccount));

        // create new accounts for created customer
        ICheckingService checkingService = new CheckingService();
        checkingService.writeToDb(new CheckingAccount(0L, 777, 555555555 ));
        ISavingsService savingsService = new SavingsService();
        savingsService .writeToDb(new SavingsAccount(0L, 0.01, 777, 555555555 ));
        savingsService.writeToDb(new SavingsAccount(0L, 0.02, 777, 555555555 ));
        ICreditCardService creditCardService = new CreditCardService();
        creditCardService.writeToDb(new CreditCardAccount(0L, 0.2, 0, 555555555));
        creditCardService.writeToDb(new CreditCardAccount(0L, 0.3, 0, 555555555));

        // queries full customer info
        LOGGER.info(customerService.readFromDb(555555555, true));

        // deleting info
        savingsService.removeFromDb(2000000000000007L);
        savingsService.removeFromDb(2000000000000008L);
        creditCardService.removeFromDb(1111111111111115L);
        creditCardService.removeFromDb(1111111111111116L);
        checkingService.removeFromDb(1000000000000004L);
        addressService.removeFromDb(100);
        customerService.removeFromDb(555555555);

        // queries all info
        LOGGER.info(savingsService.readAllFromDb());
        LOGGER.info(creditCardService.readAllFromDb());
        LOGGER.info(checkingService.readAllFromDb());
        LOGGER.info(customerService.readAllFromDb(false));
        LOGGER.info(addressService.readAllFromDb(false));

        // sax parser
        ICustomerParserService customerParserService = new CustomerParserService();
        LOGGER.info(customerParserService.getResult("src/main/resources/xml/customer.xml"));
        LOGGER.info(customerParserService.validate("src/main/resources/xml/customer.xml"));
    }
}
