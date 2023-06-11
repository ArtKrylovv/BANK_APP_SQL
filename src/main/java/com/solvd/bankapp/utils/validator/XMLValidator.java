package com.solvd.bankapp.utils.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;

import javax.xml.validation.Validator;
import javax.xml.XMLConstants;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;


public class XMLValidator {
    private static final Logger LOGGER = LogManager.getLogger(Validator.class);

    public Validator get(String xsd){
        File xsdFile = new File(xsd);
        Schema schema = null;
        // validator
        String schemaLang = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        // validator
        SchemaFactory factory = SchemaFactory.newInstance(schemaLang);
        try {
            factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        } catch (SAXNotRecognizedException | SAXNotSupportedException e){
            LOGGER.error(e.getMessage());
        }
        try {
            schema = factory.newSchema(xsdFile);
        } catch (org.xml.sax.SAXException e){
            LOGGER.error(e.getMessage());
        }
        return schema.newValidator();
    }
}

