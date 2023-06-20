package com.solvd.bankapp.mappers;

import com.solvd.bankapp.bin.CheckingAccount;

import java.util.List;

public interface CheckingMapper {
    CheckingAccount selectAccountById(long id);
    List<CheckingAccount> selectAccountAll();
    void createChecking(CheckingAccount checkingAccount);
    void updateChecking(CheckingAccount checkingAccount);
    void deleteChecking(long id);
}
