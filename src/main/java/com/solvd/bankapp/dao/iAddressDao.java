package com.solvd.bankapp.dao;

import com.solvd.bankapp.model.Address;

import java.sql.SQLException;

public interface iAddressDao extends iDao<Address> {
    int getStateIdByAddressId(int id) throws SQLException;
}
