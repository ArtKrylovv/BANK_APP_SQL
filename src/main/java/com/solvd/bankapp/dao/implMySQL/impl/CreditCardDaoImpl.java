package com.solvd.bankapp.dao.implMySQL.impl;

import com.solvd.bankapp.dao.ICreditCardDao;
import com.solvd.bankapp.bin.CreditCardAccount;
import com.solvd.bankapp.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CreditCardDaoImpl implements ICreditCardDao {
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final String sqlGetAll = "SELECT Balance, Interest, Customers_Id, account_number FROM Credit_card_accounts";
    private static final String sqlGetById = "SELECT Balance, Interest, Customers_Id FROM Credit_card_accountsv WHERE account_number =?";
    private static final String sqlCreate = "INSERT into Credit_card_accounts (Balance, Interest, Customers_Id) VALUES (?,?,?)";
    private static final String getCreditAccountsListBySsn = "SELECT Balance, Interest, Customers_Id, Account_number FROM Credit_card_accounts WHERE Customers_Id =?";
    private static final String sqlUpdate = "UPDATE Credit_card_accounts SET Balance =?, Interest=?, Customers_Id=? WHERE account_number =?";
    private static final String sqlDelete = "DELETE FROM Credit_card_accounts WHERE account_number = ?";
    private static final Logger LOGGER = LogManager.getLogger(CreditCardDaoImpl.class);

    @Override
    public List<CreditCardAccount> getAll() {
        List<CreditCardAccount> creditCardAccounts = new ArrayList<>();
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(sqlGetAll);
             ResultSet resultSet = ps.executeQuery()){
            while (resultSet.next()) {
                long balance = resultSet.getLong("balance");
                double interest = resultSet.getDouble("interest");
                int customerId = resultSet.getInt("customers_id");
                long accountNumber = resultSet.getLong("account_number");
                creditCardAccounts.add(new CreditCardAccount(accountNumber, interest, balance, customerId));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return creditCardAccounts;
    }

    @Override
    public CreditCardAccount get(int id) {
        throw new UnsupportedOperationException("use overloaded method with long type");
    }
    @Override
    public CreditCardAccount get(long id) {
        CreditCardAccount creditCardAccount = null;
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(sqlGetById)) {
            ps.setLong(1, id);
            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    long balance = resultSet.getLong("balance");
                    double interest = resultSet.getDouble("interest");
                    int customerId = resultSet.getInt("customers_id");
                    creditCardAccount = new CreditCardAccount(id, interest, balance, customerId);
                }
            }
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            } finally {
                connectionPool.releaseConnection(connection);
        }
        return creditCardAccount;
    }

    @Override
    public int create(CreditCardAccount creditCardAccount) {
        Connection connection = connectionPool.getConnection();
        int rs = 0;
        try (PreparedStatement ps = connection.prepareStatement(sqlCreate)){
            ps.setLong(1, creditCardAccount.getBalance());
            ps.setDouble(2, creditCardAccount.getInterest());
            ps.setInt(3, creditCardAccount.getCustomerId());
            rs = ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return rs;
    }

    @Override
    public int update(CreditCardAccount creditCardAccount) {
        Connection connection = connectionPool.getConnection();
        int rs = 0;
        try (PreparedStatement ps = connection.prepareStatement(sqlUpdate)){
            ps.setLong(1, creditCardAccount.getBalance());
            ps.setDouble(2, creditCardAccount.getInterest());
            ps.setInt(3, creditCardAccount.getCustomerId());
            ps.setLong(4, creditCardAccount.getAccountNumber());
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
    public List<CreditCardAccount> getCreditCardAccountsListBySsn(int ssn) {
        List<CreditCardAccount> creditCardAccountList = new ArrayList<>();
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(getCreditAccountsListBySsn)) {
            ps.setInt(1, ssn);
            try (ResultSet resultSet = ps.executeQuery()) {
                while (resultSet.next()) {
                    long balance = resultSet.getLong("balance");
                    double interest = resultSet.getDouble("interest");
                    int customerId = resultSet.getInt("customers_id");
                    long accountNumber = resultSet.getLong("account_number");
                    creditCardAccountList.add(new CreditCardAccount(accountNumber, interest, balance, customerId));
                }
            }
        } catch (SQLException e){
            LOGGER.error(e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return creditCardAccountList;
    }
}
