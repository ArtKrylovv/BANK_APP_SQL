package com.solvd.bankapp.dao;

import com.solvd.bankapp.bin.CreditCardAccount;

import java.sql.SQLException;
import java.util.List;

public interface ICreditCardDao extends IDao<CreditCardAccount> {
    int delete(long id);
    CreditCardAccount get(long id) ;
    List<CreditCardAccount> getCreditCardAccountsListBySsn(int ssn);
}
