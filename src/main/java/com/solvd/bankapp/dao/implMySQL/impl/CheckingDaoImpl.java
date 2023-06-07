package com.solvd.bankapp.dao.implMySQL.impl;

import com.solvd.bankapp.dao.ICheckingDao;
import com.solvd.bankapp.bin.CheckingAccount;
import com.solvd.bankapp.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CheckingDaoImpl implements ICheckingDao {
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final Logger LOGGER = LogManager.getLogger(CheckingDaoImpl.class);
    String sqlGetAll = "SELECT Balance, Customers_Id, account_number FROM Checking_accounts";
    String sqlGetById = "SELECT Balance, Customers_Id, account_number FROM Checking_accounts WHERE account_number =?";
    String sqlCreate = "INSERT INTO Checking_accounts (Balance, Customers_Id) VALUES (?, ?)";
    String sqlUpdate = "UPDATE Checking_accounts SET Balance=?, Customers_Id=? WHERE Account_number= ?";
    String sqlDelete = "DELETE FROM Checking_accounts WHERE Account_number= ?";
    String sqlGetCheckingAccountBySsn = "SELECT Balance, Customers_Id, account_number FROM Checking_accounts WHERE customers_id =?";


    @Override
    public List<CheckingAccount> getAll() {
        List<CheckingAccount> checkingAccountsList = new ArrayList<>();
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(sqlGetAll);
             ResultSet resultSet = ps.executeQuery()){
            while (resultSet.next()) {
                long balance = resultSet.getLong("balance");
                int customerId = resultSet.getInt("customers_id");
                long accountNumber = resultSet.getLong("account_number");
                checkingAccountsList.add(new CheckingAccount(accountNumber, balance, customerId));
            }
        } catch (SQLException e){
            LOGGER.error(e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return checkingAccountsList;
    }

    @Override
    public CheckingAccount get(int id) {
        throw new UnsupportedOperationException("use overloaded method with long type");
    }

    @Override
    public CheckingAccount get(long id) {
        CheckingAccount checkingAccount = null;
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(sqlGetById);) {
            ps.setLong(1, id);
            try ( ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    long balance = resultSet.getLong("balance");
                    int customerId = resultSet.getInt("customers_id");
                    long accountNumber = resultSet.getLong("account_number");
                    checkingAccount = new CheckingAccount(accountNumber, balance, customerId);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }finally {
            connectionPool.releaseConnection(connection);
        }
        return checkingAccount;
    }

    @Override
    public int create(CheckingAccount checkingAccount) {
        int rs = 0;
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(sqlCreate)) {
            ps.setLong(1, checkingAccount.getBalance());
            ps.setInt(2, checkingAccount.getCustomerId());
            rs = ps.executeUpdate();
        } catch (SQLException e){
            LOGGER.error(e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return rs;
    }

    @Override
    public int update(CheckingAccount checkingAccount) {
        int rs = 0;
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(sqlUpdate)) {
            ps.setLong(1, checkingAccount.getBalance());
            ps.setInt(2, checkingAccount.getCustomerId());
            ps.setLong(3, checkingAccount.getAccountNumber());
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
        throw new UnsupportedOperationException("use overloaded method with long type");
    }

    @Override
    public int delete(long id) {
        Connection connection = connectionPool.getConnection();
        int rs = 0;
        try (PreparedStatement ps = connection.prepareStatement(sqlDelete)) {
            ps.setLong(1, id);
            rs = ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return rs;
    }


    @Override
    public CheckingAccount getCheckingAccountBySsn(int ssn) {
        CheckingAccount checkingAccount = null;
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(sqlGetCheckingAccountBySsn)) {
            ps.setInt(1, ssn);
            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    long balance = resultSet.getLong("balance");
                    int customerId = resultSet.getInt("customers_id");
                    long accountNumber = resultSet.getLong("account_number");
                    checkingAccount = new CheckingAccount(accountNumber, balance, customerId);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return checkingAccount;
    }
}
