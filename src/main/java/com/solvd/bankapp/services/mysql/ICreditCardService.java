package com.solvd.bankapp.services.mysql;

import com.solvd.bankapp.bin.CreditCardAccount;
import com.solvd.bankapp.services.IService;

public interface ICreditCardService extends IService<CreditCardAccount> {
    CreditCardAccount readFromDb(long id);
    int removeFromDb(long id);
}
