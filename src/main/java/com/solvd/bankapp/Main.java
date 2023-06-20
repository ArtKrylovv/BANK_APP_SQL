package com.solvd.bankapp;

import com.solvd.bankapp.bin.Address;
import com.solvd.bankapp.bin.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Properties;

public class  Main {
    public static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        try (FileInputStream file = new FileInputStream("src/main/java/com/solvd/bankapp/impl.properties")) {
            Properties properties = new Properties();
            properties.load(file);
            // getting package and class name from .properties
            String className = properties.getProperty("class.name");

            // importing CustomerService class
            Class<?> CustomerService = Class.forName(className);
            // creating instance of CustomerService class
            Constructor<?> constructor = CustomerService.getConstructor();
            Object customerService = constructor.newInstance();
            // getting of CustomerService methods
            Method readFromDb = customerService.getClass().getDeclaredMethod("readFromDb", int.class, boolean.class);
            Method readAllFromDb = customerService.getClass().getDeclaredMethod("readAllFromDb", boolean.class);
            Method writeToDb = customerService.getClass().getDeclaredMethod("writeToDb", Customer.class);
            Method updateInDb = customerService.getClass().getDeclaredMethod("updateInDb", Customer.class);
            Method removeFromDb = customerService.getClass().getDeclaredMethod("removeFromDb", int.class);

            // calling CustomerService methods
            Customer customer1 = new Customer(22222222, "Art", "Krylov", new Address(1), null, null, null);
            Customer customer2 = new Customer(22222222, "Arti", "Krylov", new Address(1), null, null, null);
            LOGGER.info(writeToDb.invoke(customerService,customer1));
            LOGGER.info(readFromDb.invoke(customerService, 22222222, false));
            LOGGER.info(updateInDb.invoke(customerService, customer2));
            LOGGER.info(readFromDb.invoke(customerService, 22222222, false));
            LOGGER.info(removeFromDb.invoke(customerService, 22222222));
            LOGGER.info(readAllFromDb.invoke(customerService, true));


        } catch (IOException | RuntimeException | ReflectiveOperationException e) {
                LOGGER.error(e.getMessage());
            }
    }
}


