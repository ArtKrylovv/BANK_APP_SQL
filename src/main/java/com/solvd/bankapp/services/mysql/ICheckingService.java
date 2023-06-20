package com.solvd.bankapp.services.mysql;

import com.solvd.bankapp.bin.CheckingAccount;
import com.solvd.bankapp.services.parsers.IService;

public interface ICheckingService extends IService<CheckingAccount> {
    int removeFromDb(long id);
    CheckingAccount readFromDb(long id);
}
