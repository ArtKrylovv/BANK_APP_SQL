package com.solvd.bankapp.dao;

import com.solvd.bankapp.model.CheckingAccount;
import com.solvd.bankapp.model.SavingsAccount;

import java.sql.SQLException;
import java.util.List;

public interface iCheckingDao extends iDao<CheckingAccount> {
    public CheckingAccount getCheckingAccountBySsn(int ssn) throws SQLException;
}
