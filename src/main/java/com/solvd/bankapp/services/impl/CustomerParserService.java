package com.solvd.bankapp.services.impl;

import com.solvd.bankapp.bin.Address;
import com.solvd.bankapp.bin.Customer;
import com.solvd.bankapp.handlers.AddressHandler;
import com.solvd.bankapp.handlers.CustomerHandler;
import com.solvd.bankapp.utils.parser.Parser;
import com.solvd.bankapp.utils.validator.XMLValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.SAXParser;
import javax.xml.transform.sax.SAXSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CustomerParserService implements ICustomerParserService {
    private static final Logger LOGGER = LogManager.getLogger(CustomerParserService.class);
    private static final String SCHEMA = "src/main/resources/xml/customer.xsd";

    @Override
    public Customer getResult(String uri) {
        CustomerHandler customerHandler = new CustomerHandler();
        AddressHandler addressHandler = new AddressHandler();
        SAXParser saxParser = new Parser().get();
        try {
            saxParser.parse(uri, customerHandler);
        } catch (SAXException | IOException e) {
            LOGGER.error(e.getMessage());
        }
        Customer customer = customerHandler.getResult();

        try {
            saxParser.parse(uri, addressHandler);
        } catch (SAXException | IOException e) {
            LOGGER.error(e.getMessage());
        }

        Address address = addressHandler.getResult();
        customer.setAddress(address);
        return customer;
    }

    @Override
    public String validate(String uriXml) {
        Reader reader = null;
        try {
            Path xmlPath = Paths.get("src/main/resources/xml/customer.xml");
            reader = Files.newBufferedReader(xmlPath);
            SAXSource source = new SAXSource(new InputSource(reader));
            Validator validator = new XMLValidator().get(SCHEMA);
            validator.validate(source);

        } catch (SAXException | IOException e ) {
            System.out.println(e.getMessage());

        } finally {
            try {
                if(reader!=null) {
                    reader.close();
                }
            } catch (IOException e){
                LOGGER.error(e.getMessage());
            }
        }
        return "The document validation is OK";
    }
}

