package com.solvd.bankapp.dao.daoMySQL.impl;

import com.solvd.bankapp.dao.iCheckingDao;
import com.solvd.bankapp.model.CheckingAccount;
import com.solvd.bankapp.utils.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CheckingDaoImpl implements iCheckingDao {
    @Override
    public List<CheckingAccount> getAll() throws SQLException {
        List<CheckingAccount> checkingAccountsList = new ArrayList<>();
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        String sql = "SELECT Balance, Customers_Id, account_number FROM Checking_accounts";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()) {
            long balance = resultSet.getLong("balance");
            int customerId = resultSet.getInt("customers_id");
            long accountNumber = resultSet.getLong("account_number");
            checkingAccountsList.add(new CheckingAccount(accountNumber, balance, customerId));
        }
        connectionPool.releaseConnection(connection);
        return checkingAccountsList;
    }

    @Override
    public CheckingAccount get(int id) throws SQLException {
        return null;
    }

    // overloads with long id
    public CheckingAccount get(long id) throws SQLException {
        CheckingAccount checkingAccount = null;
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        String sql = "SELECT Balance, Customers_Id, account_number FROM Checking_accounts WHERE account_number =?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setLong(1, id);
        ResultSet resultSet = ps.executeQuery();
        if (resultSet.next()) {
            long balance = resultSet.getLong("balance");
            int customerId = resultSet.getInt("customers_id");
            long accountNumber = resultSet.getLong("account_number");
            checkingAccount = new CheckingAccount(accountNumber, balance, customerId);
        }
        connectionPool.releaseConnection(connection);
        return checkingAccount;
    }

    @Override
    public int create(CheckingAccount checkingAccount) throws SQLException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        String sql = "INSERT INTO Checking_accounts (Balance, Customers_Id) VALUES (?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setLong(1, checkingAccount.getBalance());
        ps.setInt(2, checkingAccount.getCustomerId());
        int rs = ps.executeUpdate();
        ps.close();
        connectionPool.releaseConnection(connection);
        return rs;
    }

    @Override
    public int update(CheckingAccount checkingAccount) throws SQLException {
        return 0;
    }

    @Override
    public int delete(int id) throws SQLException {
        return 0;
    }


    @Override
    public CheckingAccount getCheckingAccountBySsn(int ssn) throws SQLException {
        CheckingAccount checkingAccount = null;
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        String sql = "SELECT Balance, Customers_Id, account_number FROM Checking_accounts WHERE customers_id =?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, ssn);
        ResultSet resultSet = ps.executeQuery();
        if (resultSet.next()) {
            long balance = resultSet.getLong("balance");
            int customerId = resultSet.getInt("customers_id");
            long accountNumber = resultSet.getLong("account_number");
            checkingAccount = new CheckingAccount(accountNumber, balance, customerId);
        }
        connectionPool.releaseConnection(connection);
        return checkingAccount;
    }
}
