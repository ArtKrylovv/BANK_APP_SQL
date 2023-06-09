package com.solvd.bankapp.handlers;

import com.solvd.bankapp.bin.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class AddressHandler extends DefaultHandler {
    private static final String ID = "id";
    private static final String HOUSE_NUMBER = "houseNumber";
    private static final String STREET_NAME = "streetName";
    private static final String CITY = "city";
    private static final String APT_NUMBER = "aptNumber";
    private static final String STATE = "state";

    private Address address;
    private StringBuilder elementValue;

    public Address getResult() {
        return address;
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
        address = new Address();
    }

    // startElement() is invoked when the parsing begins for an element
    // – qName helps us make the distinction between both types
    public void startElement(String uri, String lName, String qName, Attributes attr) {
        switch (qName) {
            case ID:
            case HOUSE_NUMBER:
            case STREET_NAME:
            case APT_NUMBER:
            case CITY:
                elementValue = new StringBuilder();
                break;
            case STATE:
                address.setState(new State());
                break;
        }
    }
        //assigns the content of the tags to their respective variables
        @Override
        public void endElement(String uri, String localName, String qName){
            switch (qName) {
                case HOUSE_NUMBER:
                    address.setHouseNumber(Integer.parseInt(elementValue.toString()));
                    break;

                case STREET_NAME:
                    address.setStreetName(elementValue.toString());
                    break;

                case ID:
                    address.setId(Integer.parseInt(elementValue.toString()));
                    break;

                case CITY:
                    address.setCity(elementValue.toString());
                    break;
                case APT_NUMBER:
                    if (elementValue.length()!=0) {
                        address.setAptNumber(Integer.parseInt(elementValue.toString()));
                    }
                    break;
            }
        }
}




