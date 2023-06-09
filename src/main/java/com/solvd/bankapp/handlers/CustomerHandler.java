package com.solvd.bankapp.handlers;

import com.solvd.bankapp.bin.*;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class CustomerHandler extends DefaultHandler {
    private static final String SSN = "ssn";
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String ADDRESS = "address";
    private static final String CHECKING_ACCOUNT = "checkingAccount";

    private Customer customer;
    private StringBuilder elementValue;

    public Customer getResult() {
        return customer;
    }

    @Override
    // receives characters with boundaries. We'll convert them to a String and store it in a variable of CustomerHandler
    public void characters(char[] ch, int start, int length) {
        if (elementValue == null) {
            elementValue = new StringBuilder();
        } else {
            elementValue.append(ch, start, length);
        }
    }

    @Override
    // startDocument() is invoked when the parsing begins – we'll use it to construct our Customer instance
    public void startDocument() {
        customer = new Customer();
    }

    // startElement() is invoked when the parsing begins for an element
    // – qName helps us make the distinction between both types
    public void startElement(String uri, String lName, String qName, Attributes attr) {
        switch (qName) {
            case FIRST_NAME:
            case LAST_NAME:
            case SSN:
                elementValue = new StringBuilder();
                break;
            case ADDRESS:
                customer.setAddress(new Address());
                break;
            case CHECKING_ACCOUNT:
                customer.setCheckingAccount(new CheckingAccount());
                // sets Savings and Credit Cards while XML doesn't support these Classes
                List<SavingsAccount> savingsList = new ArrayList<>();
                List<CreditCardAccount> creditList = new ArrayList<>();
                customer.setSavingAccountsList(savingsList);
                customer.setCreditCardAccounts(creditList);
        }
    }
        //assigns the content of the tags to their respective variables
        @Override
        public void endElement(String uri, String localName, String qName){
            switch (qName) {
                case FIRST_NAME:
                    customer.setFirstName(elementValue.toString());
                    break;

                case LAST_NAME:
                    customer.setLastName(elementValue.toString());
                    break;

                case SSN:
                    customer.setSsn(Integer.parseInt(elementValue.toString()));
                    break;
            }
        }
}




