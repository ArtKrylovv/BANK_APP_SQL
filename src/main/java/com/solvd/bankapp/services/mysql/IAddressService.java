package com.solvd.bankapp.services.mysql;

import com.solvd.bankapp.bin.Address;
import com.solvd.bankapp.services.parsers.IService;

import java.util.List;

public interface IAddressService extends IService<Address> {
    int getStateIdByAddressId(int id);
    Address readFromDb(int id, boolean full);
    List<Address> readAllFromDb(boolean full);
}
