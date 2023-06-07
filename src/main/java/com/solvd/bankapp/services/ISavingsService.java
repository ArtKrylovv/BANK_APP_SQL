package com.solvd.bankapp.services;

import com.solvd.bankapp.bin.SavingsAccount;

public interface ISavingsService extends IService <SavingsAccount> {
    SavingsAccount readFromDb(long id);
    int removeFromDb(long id);
}
