package com.solvd.bankapp.dao;

import com.solvd.bankapp.model.Customer;
import com.solvd.bankapp.model.SavingsAccount;

import java.sql.SQLException;
import java.util.List;

public interface iSavingsDao extends iDao<SavingsAccount>{
    public List<SavingsAccount> getSavingsAccountsListBySsn(int ssn) throws SQLException;

}
