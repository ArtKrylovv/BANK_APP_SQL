package com.solvd.bankapp.utils.db;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ConnectionPool {
    // connection pool object is not created at class compilation, allowing for lazy initialization
    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);
    public static ConnectionPool connectionPool;
    private final List<Connection> connectionList;

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
            Properties props = PropertiesDb.get();
            try {
                Connection connection = DriverManager.getConnection(props.getProperty("db.url"), props.getProperty("db.user"), props.getProperty("db.password"));
                connectionList.add(connection);
            } catch (SQLException e){
                LOGGER.error(e.getMessage());
            }
        }
    }
    // returns connection if the connections list is not empty
    public synchronized Connection getConnection ()  {
        while (connectionList.isEmpty()){
            try {
                wait();
            } catch (InterruptedException e) {
                LOGGER.error(e.getMessage());
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