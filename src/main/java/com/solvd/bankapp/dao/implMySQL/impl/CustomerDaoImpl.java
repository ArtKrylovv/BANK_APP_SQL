package com.solvd.bankapp.dao.implMySQL.impl;

import com.solvd.bankapp.dao.ICustomerDao;
import com.solvd.bankapp.bin.*;
import com.solvd.bankapp.utils.db.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements ICustomerDao {
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final Logger LOGGER = LogManager.getLogger(CustomerDaoImpl.class);
    private static final String sqlGetAll = "SELECT SSN, First_name, Last_name, Addresses_Id FROM Customers";
    private static final String sqlGetById = "SELECT SSN, First_name, Last_name, Addresses_Id FROM Customers WHERE SSN =?";
    private static final String sqlCreate = "INSERT INTO Customers (SSN, First_name, Last_name, Addresses_Id) VALUES (?, ?, ?, ?)";
    private static final String sqlUpdate = "UPDATE Customers SET First_name =?, Last_name =?, Addresses_Id =? WHERE SSN =?";
    private static final String sqlDelete = "DELETE FROM Customers WHERE SSN = ?";
    private static final String sqlGetAddressIdBySsn = "SELECT Addresses_Id FROM Customers WHERE SSN=?";

    @Override
    public List<Customer> getAll() {
        List<Customer> customersList = new ArrayList<>();
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(sqlGetAll);
             ResultSet resultSet = ps.executeQuery()) {
            while (resultSet.next()) {
                int ssn = resultSet.getInt("ssn");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                Address address = null;
                List<SavingsAccount> savingAccountsList = new ArrayList<>();
                List<CreditCardAccount> creditCardAccountsList = new ArrayList<>();
                CheckingAccount checkingAccount = null;
                customersList.add(new Customer(ssn, firstName, lastName, address, savingAccountsList,
                        creditCardAccountsList, checkingAccount));
            }
        } catch (SQLException e){
            LOGGER.error(e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return customersList;
    }

    @Override
    public Customer get(int id) {
        Customer customer = null;
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(sqlGetById)) {
            ps.setInt(1, id);
            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    int ssn = resultSet.getInt("ssn");
                    String firstName = resultSet.getString("first_name");
                    String lastName = resultSet.getString("last_name");
                    Address address = null;
                    List<SavingsAccount> savingAccountsList = new ArrayList<>();
                    List<CreditCardAccount> creditCardAccountsList = new ArrayList<>();
                    CheckingAccount checkingAccount = null;
                    customer = new Customer(ssn, firstName, lastName, address, savingAccountsList,
                            creditCardAccountsList, checkingAccount);
                }
            }
        } catch (SQLException e){
            LOGGER.error(e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return customer;
    }

    @Override
    public int create(Customer customer) {
        int rs = 0;
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(sqlCreate)) {
            ps.setInt(1, customer.getSsn());
            ps.setString(2, customer.getFirstName());
            ps.setString(3, customer.getLastName());
            ps.setInt(4, customer.getAddress().getId());
            rs = ps.executeUpdate();
        } catch (SQLException e){
            LOGGER.error(e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return rs;
    }

    @Override
    public int update(Customer customer) {
        int rs = 0;
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(sqlUpdate)) {
            ps.setString(1, customer.getFirstName());
            ps.setString(2, customer.getLastName());
            ps.setInt(3, customer.getAddress().getId());
            ps.setInt(4, customer.getSsn());
            rs = ps.executeUpdate();
        } catch (SQLException e){
            LOGGER.error(e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return rs;
    }

    @Override
    public int delete(int id) {
        int rs =0;
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(sqlDelete)){
            ps.setInt(1, id);
            rs = ps.executeUpdate();
        } catch (SQLException e){
            LOGGER.error(e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return rs;
    }

    @Override
    public int getAddressIdBySsn(int ssn) {
        int addressId = 0;
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(sqlGetAddressIdBySsn)) {
            ps.setInt(1, ssn);
            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    addressId = resultSet.getInt("Addresses_id");
                }
            }
        } catch (SQLException e){
            LOGGER.error(e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return addressId;
    }
}
