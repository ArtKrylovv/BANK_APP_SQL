package com.solvd.bankapp.services;

import com.solvd.bankapp.bin.CheckingAccount;
import com.solvd.bankapp.bin.CreditCardAccount;

public interface ICreditCardService extends IService <CreditCardAccount> {
    CreditCardAccount readFromDb(long id);
    int removeFromDb(long id);
}
