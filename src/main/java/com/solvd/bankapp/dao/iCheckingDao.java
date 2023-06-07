package com.solvd.bankapp.dao;

import com.solvd.bankapp.model.CheckingAccount;
import com.solvd.bankapp.model.CreditCardAccount;
import com.solvd.bankapp.model.SavingsAccount;

import java.sql.SQLException;
import java.util.List;

public interface iCheckingDao extends iDao<CheckingAccount> {
    int delete(long id) throws SQLException;
    CheckingAccount get(long id) throws SQLException;
    CheckingAccount getCheckingAccountBySsn(int ssn) throws SQLException;
}
