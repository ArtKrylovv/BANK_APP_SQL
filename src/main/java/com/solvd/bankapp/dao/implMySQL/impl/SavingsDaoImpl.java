package com.solvd.bankapp.dao.implMySQL.impl;

import com.solvd.bankapp.dao.ISavingsDao;
import com.solvd.bankapp.bin.SavingsAccount;
import com.solvd.bankapp.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SavingsDaoImpl implements ISavingsDao {
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final String sqlGetAll = "SELECT Balance, Interest, Customers_Id, account_number FROM Saving_accounts";
    private static final String sqlGetById = "SELECT Balance, Interest, Customers_Id FROM Saving_accounts WHERE account_number =?";
    private static final String sqlCreate = "INSERT into Saving_accounts (Balance, Interest, Customers_Id) VALUES (?,?,?)";
    private static final String getSavingsAccountsListBySsn = "SELECT Balance, Interest, Customers_Id, Account_number FROM Saving_accounts WHERE Customers_Id =?";
    private static final String sqlUpdate = "UPDATE Saving_accounts SET Balance =?, Interest=?, Customers_Id=? WHERE account_number =?";
    private static final String sqlDelete = "DELETE FROM Saving_accounts WHERE account_number = ?";
    private static final Logger LOGGER = LogManager.getLogger(SavingsAccount.class);

    @Override
    public List<SavingsAccount> getAll() {
        List<SavingsAccount> savingAccountsList = new ArrayList<>();
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(sqlGetAll);
             ResultSet resultSet = ps.executeQuery()){
            while (resultSet.next()) {
                long balance = resultSet.getLong("balance");
                double interest = resultSet.getDouble("interest");
                int customerId = resultSet.getInt("customers_id");
                long accountNumber = resultSet.getLong("account_number");
                savingAccountsList.add(new SavingsAccount(accountNumber, interest, balance, customerId));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return savingAccountsList;
    }

    @Override
    public SavingsAccount get(int id) {
        throw new UnsupportedOperationException("use overloaded method with long type");
    }
    // overloads with long id
    public SavingsAccount get(long id) {
        SavingsAccount savingsAccount = null;
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(sqlGetById)) {
            ps.setLong(1, id);
            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    long balance = resultSet.getLong("balance");
                    double interest = resultSet.getDouble("interest");
                    int customerId = resultSet.getInt("customers_id");
                    savingsAccount = new SavingsAccount(id, interest, balance, customerId);
                }
            }
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            } finally {
                connectionPool.releaseConnection(connection);
        }
        return savingsAccount;
    }

    @Override
    public int create(SavingsAccount savingsAccount) {
        Connection connection = connectionPool.getConnection();
        int rs = 0;
        try (PreparedStatement ps = connection.prepareStatement(sqlCreate)){
            ps.setLong(1, savingsAccount.getBalance());
            ps.setDouble(2, savingsAccount.getInterest());
            ps.setInt(3, savingsAccount.getCustomerId());
            rs = ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return rs;
    }

    @Override
    public int update(SavingsAccount savingsAccount) {
        Connection connection = connectionPool.getConnection();
        int rs = 0;
        try (PreparedStatement ps = connection.prepareStatement(sqlUpdate)){
            ps.setLong(1, savingsAccount.getBalance());
            ps.setDouble(2, savingsAccount.getInterest());
            ps.setInt(3, savingsAccount.getCustomerId());
            ps.setLong(4, savingsAccount.getAccountNumber());
            rs = ps.executeUpdate();
        } catch (SQLException e) {
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

    // overloads with long id
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
    public List<SavingsAccount> getSavingsAccountsListBySsn(int ssn) {
        List<SavingsAccount> savingsAccountsList = new ArrayList<>();
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(getSavingsAccountsListBySsn)) {
            ps.setInt(1, ssn);
            try (ResultSet resultSet = ps.executeQuery()) {
                while (resultSet.next()) {
                    long balance = resultSet.getLong("balance");
                    double interest = resultSet.getDouble("interest");
                    int customerId = resultSet.getInt("customers_id");
                    long accountNumber = resultSet.getLong("account_number");
                    savingsAccountsList.add(new SavingsAccount(accountNumber, interest, balance, customerId));
                }
            }
        } catch (SQLException e){
            LOGGER.error(e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return savingsAccountsList;
    }
}
