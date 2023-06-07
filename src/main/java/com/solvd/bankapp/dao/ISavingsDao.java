package com.solvd.bankapp.dao;

import com.solvd.bankapp.bin.SavingsAccount;

import java.sql.SQLException;
import java.util.List;

public interface ISavingsDao extends IDao<SavingsAccount> {
    SavingsAccount get(long id);
    int delete(long id);
    List<SavingsAccount> getSavingsAccountsListBySsn(int ssn);

}
