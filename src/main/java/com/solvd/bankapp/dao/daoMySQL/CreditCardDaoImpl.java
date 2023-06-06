package com.solvd.bankapp.dao.daoMySQL;

import com.solvd.bankapp.dao.iCreditCardDao;
import com.solvd.bankapp.model.CreditCardAccount;
import com.solvd.bankapp.model.SavingsAccount;
import com.solvd.bankapp.utils.ConnectionUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CreditCardDaoImpl implements iCreditCardDao {
    @Override
    public List<CreditCardAccount> getAll() throws SQLException {
        List<CreditCardAccount> creditCardAccountsList = new ArrayList<>();
        Connection connection = ConnectionUtils.getConnection();
        String sql = "SELECT Balance, Interest, Customers_Id, account_number FROM Credit_card_accounts";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()) {
            long balance = resultSet.getLong("balance");
            double interest = resultSet.getDouble("interest");
            int customerId = resultSet.getInt("customers_id");
            long accountNumber = resultSet.getLong("account_number");
            creditCardAccountsList.add(new CreditCardAccount(accountNumber, interest, balance, customerId));
        }
        return creditCardAccountsList;
    }

    @Override
    public CreditCardAccount get(int id) throws SQLException {
        return null;
    }

    // overloads with long id
    public CreditCardAccount get(long id) throws SQLException {
        CreditCardAccount creditCardAccount = null;
        Connection connection = ConnectionUtils.getConnection();
        String sql = "SELECT Balance, Interest, Customers_Id FROM Credit_card_accounts WHERE account_number =?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setLong(1, id);
        ResultSet resultSet = ps.executeQuery();
        if (resultSet.next()) {
            long balance = resultSet.getLong("balance");
            double interest = resultSet.getDouble("interest");
            int customerId = resultSet.getInt("customers_id");
            creditCardAccount = new CreditCardAccount(id, interest, balance, customerId);
        }
        return creditCardAccount;
    }

    @Override
    public int create(CreditCardAccount creditCardAccount) throws SQLException {
        return 0;
    }

    @Override
    public int update(CreditCardAccount creditCardAccount) throws SQLException {
        return 0;
    }

    @Override
    public int delete(int id) throws SQLException {
        return 0;
    }

    @Override
    public List<CreditCardAccount> getCreditCardAccountsListBySsn(int ssn) throws SQLException {
        List<CreditCardAccount> creditCardAccountsList = new ArrayList<>();
        Connection connection = ConnectionUtils.getConnection();
        String sql = "SELECT Balance, Interest, Customers_Id, Account_number FROM Credit_card_accounts WHERE Customers_Id =?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, ssn);
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()) {
            long balance = resultSet.getLong("balance");
            double interest = resultSet.getDouble("interest");
            int customerId = resultSet.getInt("customers_id");
            long accountNumber = resultSet.getLong("account_number");
            creditCardAccountsList.add(new CreditCardAccount(accountNumber, interest, balance, customerId));
        }
        return creditCardAccountsList;
    }
}
