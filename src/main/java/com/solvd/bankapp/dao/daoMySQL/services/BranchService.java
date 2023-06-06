package com.solvd.bankapp.dao.daoMySQL.services;

import com.solvd.bankapp.dao.daoMySQL.impl.AddressDaoImpl;
import com.solvd.bankapp.dao.daoMySQL.impl.BranchDaoImpl;
import com.solvd.bankapp.dao.iAddressDao;
import com.solvd.bankapp.dao.iBranchDao;
import com.solvd.bankapp.model.Address;
import com.solvd.bankapp.model.Branch;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BranchService {
    iBranchDao branchDao = new BranchDaoImpl();
    iAddressDao addressDao = new AddressDaoImpl();

    public Branch get(int id) throws SQLException {
        Branch branch = null;
        branch = branchDao.get(id);
        int addressId = branchDao.getAddressIdByBranchId(id);
        System.out.println(addressId);
        Address address = addressDao.get(addressId);
        branch.setAddress(address);
        return branch;
    }
    public List<Branch> getAll() throws SQLException {
        List<Branch> branchesList = new ArrayList<>();
        for (Branch branch : branchDao.getAll()) {
            int addressId = addressDao.getStateIdByAddressId(branch.getId());
            Address address = addressDao.get(addressId);
            branch.setAddress(address);
            branchesList.add(branch);
        }
        return branchesList;
    }
}
