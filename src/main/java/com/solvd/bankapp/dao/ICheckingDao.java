package com.solvd.bankapp.dao;

import com.solvd.bankapp.bin.CheckingAccount;

import java.sql.SQLException;

public interface ICheckingDao extends IDao<CheckingAccount> {
    int delete(long id);
    CheckingAccount get(long id) ;
    CheckingAccount getCheckingAccountBySsn(int ssn);
}
