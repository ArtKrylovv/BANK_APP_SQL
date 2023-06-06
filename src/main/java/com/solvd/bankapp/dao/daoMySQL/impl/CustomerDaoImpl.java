package com.solvd.bankapp.dao.daoMySQL.impl;

import com.solvd.bankapp.dao.iCustomerDao;
import com.solvd.bankapp.model.*;
import com.solvd.bankapp.utils.ConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements iCustomerDao {

    @Override
    public List<Customer> getAll() throws SQLException {
        List<Customer> customersList = new ArrayList<>();
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        String sql = "SELECT SSN, First_name, Last_name, Addresses_Id FROM Customers";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()){
            int ssn = resultSet.getInt("ssn");
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            Address address = null;
            List<SavingsAccount> savingAccountsList = new ArrayList<>();
            List<CreditCardAccount> creditCardAccountsList = new ArrayList<>();
            CheckingAccount checkingAccount = null;

            customersList.add(new Customer(ssn, firstName,lastName,address, savingAccountsList,
                    creditCardAccountsList, checkingAccount));
        }
        connectionPool.releaseConnection(connection);
        return customersList;
    }

    @Override
    public Customer get(int id) throws SQLException {
        Customer customer = null;
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        String sql = "SELECT SSN, First_name, Last_name, Addresses_Id FROM Customers WHERE SSN =?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet resultSet = ps.executeQuery();
        if (resultSet.next()){
            int ssn = resultSet.getInt("ssn");
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            Address address = null;
            List<SavingsAccount> savingAccountsList = new ArrayList<>();
            List<CreditCardAccount> creditCardAccountsList = new ArrayList<>();
            CheckingAccount checkingAccount = null;
            customer = new Customer(ssn, firstName,lastName,address, savingAccountsList,
                    creditCardAccountsList, checkingAccount);
        }
        connectionPool.releaseConnection(connection);
        return customer;
    }

    @Override
    public int create(Customer customer) throws SQLException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        String sql = "INSERT INTO Customers (SSN, First_name, Last_name, Addresses_Id) VALUES (?, ?, ?, ?)";

//        INSERT INTO Customers (SSN, First_name, Last_name, Addresses_Id)
//        VALUES (100000000, "Elon", "Musk", 1),

        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, customer.getSsn());
        ps.setString(2, customer.getFirstName());
        ps.setString(3, customer.getLastName());
        ps.setInt(4, customer.getAddress().getId());
        int rs = ps.executeUpdate();
        ps.close();
        connectionPool.releaseConnection(connection);
        return rs;
    }

    @Override
    public int update(Customer customer) throws SQLException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        String sql = "UPDATE Customers SET First_name =?, Last_name =?, Addresses_Id =? WHERE SSN =?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, customer.getFirstName());
        ps.setString(2, customer.getLastName());
        ps.setInt(3, customer.getAddress().getId());
        ps.setInt(4, customer.getSsn());
        int rs = ps.executeUpdate();
        ps.close();
        connectionPool.releaseConnection(connection);
        return rs;
    }

    @Override
    public int delete(int id) throws SQLException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        String sql = "DELETE FROM Customers WHERE SSN = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        int rs = ps.executeUpdate();
        ps.close();
        connectionPool.releaseConnection(connection);
        return rs;
    }
}