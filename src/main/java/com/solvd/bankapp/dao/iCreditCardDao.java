package com.solvd.bankapp.dao;

import com.solvd.bankapp.model.CreditCardAccount;
import com.solvd.bankapp.model.SavingsAccount;

import java.sql.SQLException;
import java.util.List;

public interface iCreditCardDao extends iDao<CreditCardAccount> {
    public List<CreditCardAccount> getCreditCardAccountsListBySsn(int ssn) throws SQLException;
}
