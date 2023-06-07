package com.solvd.bankapp.dao;

import com.solvd.bankapp.model.CreditCardAccount;
import com.solvd.bankapp.model.Customer;
import com.solvd.bankapp.model.SavingsAccount;

import java.sql.SQLException;
import java.util.List;

public interface iSavingsDao extends iDao<SavingsAccount>{
    SavingsAccount get(long id) throws SQLException;
    int delete(long id) throws SQLException;
    List<SavingsAccount> getSavingsAccountsListBySsn(int ssn) throws SQLException;

}
