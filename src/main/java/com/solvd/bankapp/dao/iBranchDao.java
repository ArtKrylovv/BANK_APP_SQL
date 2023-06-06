package com.solvd.bankapp.dao;

import com.solvd.bankapp.model.Branch;

import java.sql.SQLException;

public interface iBranchDao extends iDao<Branch>  {
    int getAddressIdByBranchId(int id) throws SQLException;
}
