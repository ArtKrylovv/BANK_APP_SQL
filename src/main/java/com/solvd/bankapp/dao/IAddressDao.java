package com.solvd.bankapp.dao;

import com.solvd.bankapp.bin.Address;

import java.sql.SQLException;

public interface IAddressDao extends IDao<Address> {
    int getStateIdByAddressId(int id) ;
}
