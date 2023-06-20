package com.solvd.bankapp.services.mysql;

import com.solvd.bankapp.bin.SavingsAccount;
import com.solvd.bankapp.services.parsers.IService;

public interface ISavingsService extends IService<SavingsAccount> {
    SavingsAccount readFromDb(long id);
    int removeFromDb(long id);
}
