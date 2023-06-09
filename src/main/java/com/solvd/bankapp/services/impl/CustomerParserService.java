package com.solvd.bankapp.services.impl;

import com.solvd.bankapp.bin.Address;
import com.solvd.bankapp.bin.Customer;
import com.solvd.bankapp.handlers.AddressHandler;
import com.solvd.bankapp.handlers.CustomerHandler;
import com.solvd.bankapp.services.IParserService;
import com.solvd.bankapp.utils.parser.Parser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.parsers.SAXParser;
import java.io.IOException;

public class CustomerParserService implements IParserService <Customer>{
    private static final Logger LOGGER = LogManager.getLogger(CustomerParserService.class);

    @Override
    public Customer getResult(String uri){
        CustomerHandler customerHandler = new CustomerHandler();
        AddressHandler addressHandler = new AddressHandler();
        SAXParser saxParser = new Parser().get();
        try {
            saxParser.parse(uri, customerHandler);
        } catch(SAXException | IOException e) {
            LOGGER.error(e.getMessage());
        }
        Customer customer = customerHandler.getResult();

        try {
            saxParser.parse(uri, addressHandler);
        } catch(SAXException | IOException e) {
            LOGGER.error(e.getMessage());
        }

        Address address = addressHandler.getResult();
        customer.setAddress(address);
        return customer;
    }
}
