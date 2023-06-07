package com.solvd.bankapp.dao;

import com.solvd.bankapp.model.CreditCardAccount;
import com.solvd.bankapp.model.SavingsAccount;

import java.sql.SQLException;
import java.util.List;

public interface iCreditCardDao extends iDao<CreditCardAccount> {
    int delete(long id) throws SQLException;
    CreditCardAccount get(long id) throws SQLException;
    List<CreditCardAccount> getCreditCardAccountsListBySsn(int ssn) throws SQLException;
}
