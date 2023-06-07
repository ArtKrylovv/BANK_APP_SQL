package com.solvd.bankapp.services;

import com.solvd.bankapp.bin.Address;

import java.util.List;

public interface IAddressService extends IService <Address> {
    int getStateIdByAddressId(int id);
    Address readFromDb(int id, boolean full);
    List<Address> readAllFromDb(boolean full);
}
