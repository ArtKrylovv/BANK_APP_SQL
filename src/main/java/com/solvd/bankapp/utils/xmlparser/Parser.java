package com.solvd.bankapp.utils.xmlparser;

import org.apache.logging.log4j.LogManager;
import org.xml.sax.SAXException;
import org.apache.logging.log4j.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class Parser {
    private static final Logger LOGGER = LogManager.getLogger(Parser.class);

    //Initialize and return SAX parser
    public SAXParser get() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = null;
        try {
            saxParser = factory.newSAXParser();
        } catch (ParserConfigurationException | SAXException e) {
            LOGGER.error(e.getMessage());
        }
        return saxParser;
    }
}






