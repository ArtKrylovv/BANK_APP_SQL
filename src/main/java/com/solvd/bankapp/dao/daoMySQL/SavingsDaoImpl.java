package com.solvd.bankapp.dao.daoMySQL;

import com.solvd.bankapp.dao.iSavingsDao;
import com.solvd.bankapp.model.Address;
import com.solvd.bankapp.model.Customer;
import com.solvd.bankapp.model.SavingsAccount;
import com.solvd.bankapp.utils.ConnectionUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SavingsDaoImpl implements iSavingsDao {
    @Override
    public List<SavingsAccount> getAll() throws SQLException {
        List<SavingsAccount> savingAccountsList = new ArrayList<>();
        Connection connection = ConnectionUtils.getConnection();
        String sql = "SELECT Balance, Interest, Customers_Id FROM Saving_accounts";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()){
            long balance = resultSet.getLong("balance");
            double interest = resultSet.getDouble("interest");
            int customerId = resultSet.getInt("customers_id");
            savingAccountsList.add(new SavingsAccount(balance,interest,customerId));
        }
        return savingAccountsList;
    }

    @Override
    public SavingsAccount get(int id) throws SQLException {
        SavingsAccount savingsAccount = null;
        Connection connection = ConnectionUtils.getConnection();
        String sql = "SELECT Balance, Interest, Customers_Id FROM Saving_accounts WHERE account_number =?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet resultSet = ps.executeQuery();
        if (resultSet.next()) {
            long balance = resultSet.getLong("balance");
            double interest = resultSet.getDouble("interest");
            int customerId = resultSet.getInt("customers_id");
            savingsAccount = new SavingsAccount(balance, interest, customerId);
        }
        return savingsAccount;
    }

    @Override
    public int create(SavingsAccount savingsAccount) throws SQLException {
        return 0;
    }

    @Override
    public int update(SavingsAccount savingsAccount) throws SQLException {
        return 0;
    }

    @Override
    public int delete(int id) throws SQLException {
        return 0;
    }

    @Override
    public List<SavingsAccount> getSavingsAccountsListBySsn(int ssn) throws SQLException{
        List<SavingsAccount> savingsAccountsList = new ArrayList<>();
        Connection connection = ConnectionUtils.getConnection();
        String sql = "SELECT Balance, Interest, Customers_Id, Account_number FROM Saving_accounts WHERE Customers_Id =?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, ssn);
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()) {
            long balance = resultSet.getLong("balance");
            double interest = resultSet.getDouble("interest");
            int customerId = resultSet.getInt("customers_id");
            savingsAccountsList.add(new SavingsAccount(balance, interest, customerId));
        }
        return savingsAccountsList;
    }
}
