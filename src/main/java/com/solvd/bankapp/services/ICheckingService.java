package com.solvd.bankapp.services;

import com.solvd.bankapp.bin.CheckingAccount;

public interface ICheckingService extends IService <CheckingAccount> {
    int removeFromDb(long id);
    CheckingAccount readFromDb(long id);
}
