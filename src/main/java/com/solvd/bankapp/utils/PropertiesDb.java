package com.solvd.bankapp.utils;

import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;


public class PropertiesDb {
    private static final Logger LOGGER = LogManager.getLogger(PropertiesDb.class);
    private static final String PROPERTIES = "src/main/resources/db.properties";

    public static Properties get() {
        Properties props = new Properties();
        try (InputStream input = new FileInputStream(PROPERTIES)) {
            props.load(input);

        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
        return props;
    }
}