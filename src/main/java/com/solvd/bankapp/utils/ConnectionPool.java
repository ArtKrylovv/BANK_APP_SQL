package com.solvd.bankapp.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ConnectionPool {
    // connection pool object is not created at class compilation, allowing for lazy initialization
    public static ConnectionPool connectionPool;
    private List<Connection> connectionList;

    // constructor must be private
    private ConnectionPool() {
        connectionList = new ArrayList<>();
        initializeConnections();
    }

    // ensures thread safety + single instance of Connection pool
    public static synchronized ConnectionPool getInstance () {
        if (connectionPool == null) {
            connectionPool = new ConnectionPool();
        }
        return connectionPool;
    }
    // initializing 5 connections
    private void initializeConnections()  {
        for (int i = 0; i < 5; i++) {
            Properties props = new Properties();
            try (InputStream input = new FileInputStream("src/main/resources/db.properties")) {
                props.load(input);
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            // adds connection to the list
            try {
                Connection connection = DriverManager.getConnection(props.getProperty("db.url"), props.getProperty("db.user"), props.getProperty("db.password"));
                connectionList.add(connection);
            } catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
    }
    // returns connection if the connections list is not empty
    public synchronized Connection getConnection ()  {
        while (connectionList.isEmpty()){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
     Connection connection = connectionList.remove(connectionList.size()-1);
    return connection;
}
    // ads connection back to the list, notifies if waiting for connection
    public synchronized void releaseConnection(Connection connection){
        connectionList.add(connection);
        notify();
    }
}