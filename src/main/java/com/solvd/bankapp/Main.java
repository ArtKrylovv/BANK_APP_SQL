package com.solvd.bankapp;
import com.solvd.bankapp.bin.*;
import com.solvd.bankapp.services.*;
import com.solvd.bankapp.services.impl.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class  Main {
    public static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws IOException {

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
        checkingService.writeToDb(new CheckingAccount(0L, 777, 555555555, Date.valueOf("1991-03-12")));
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

        // JAXB marshalling
        // objects creation
        State state = new State(99, "TE", "Test");
        Address address1 = new Address(99, 9, "Test", 0, "Test", state);
        CheckingAccount checkingAccount1 = new CheckingAccount(9999999999999999L, 99, 0, Date.valueOf("1999-12-12"));
        SavingsAccount savingsAccount1 = new SavingsAccount(9999999999999998L,0.03, 99, 0 );
        SavingsAccount savingsAccount2 = new SavingsAccount(9999999999999997L,0.03, 88, 0 );
        CreditCardAccount creditCardAccount1 = new CreditCardAccount(9999999999999996L, 0.25, 0, 0);
        CreditCardAccount creditCardAccount2 = new CreditCardAccount(9999999999999995L, 0.30, 99, 0);

        List<SavingsAccount> listSavings = new ArrayList<>();
        List <CreditCardAccount> listCards = new ArrayList<>();
        listSavings.add(savingsAccount1);
        listSavings.add(savingsAccount2);
        listCards.add(creditCardAccount1);

        listCards.add(creditCardAccount2);
        Customer customer = new Customer(99999999, "Joe", "Doe", address1,
                listSavings, listCards, checkingAccount1);

        // marshalling
        customerParserService.marshall(customer);

        // JAXB unmarshalling
        LOGGER.info(customerParserService.unmarshall("src/main/resources/xml/jaxb_input.xml"));


        // JACKSON JSON deserializing
        IEmployeeJsonService employeeJsonService = new EmployeeJsonService();
        Employee employee = employeeJsonService.deserialize(new File("src/main/resources/json/jackson_input.json"));
        LOGGER.info(employee);
    }
}

