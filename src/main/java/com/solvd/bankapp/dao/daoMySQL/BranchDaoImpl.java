package com.solvd.bankapp.dao.daoMySQL;

import com.solvd.bankapp.dao.iBranchDao;
import com.solvd.bankapp.model.Address;
import com.solvd.bankapp.model.Branch;
import com.solvd.bankapp.model.State;
import com.solvd.bankapp.utils.ConnectionUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BranchDaoImpl implements iBranchDao {

    @Override
    public int getAddressIdByBranchId(int id) throws SQLException {
        int addressId = 0;
        Connection connection = ConnectionUtils.getConnection();
        String sql = "SELECT Addresses_id FROM Branches WHERE id =?";
        PreparedStatement ps =  connection.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet resultSet  = ps.executeQuery();
        if (resultSet.next()){
            addressId =  resultSet.getInt("Addresses_id");
        }
        return addressId;
    }

    @Override
    public List<Branch> getAll() throws SQLException {
        List<Branch> branchesList = new ArrayList<>();
        Connection connection = ConnectionUtils.getConnection();
        String sql = "SELECT name, Addresses_id, id FROM Branches";
        PreparedStatement ps =  connection.prepareStatement(sql);
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()){
            String name = resultSet.getString("name");
            int idBranches = resultSet.getInt("id");
            Address address = null;
            branchesList.add(new Branch(idBranches, name, address));
        }
        return branchesList;
    }


    @Override
    public Branch get(int id) throws SQLException {
        Branch branch = null;
        Connection connection = ConnectionUtils.getConnection();
        String sql = "SELECT name, Addresses_id, id FROM Branches WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet resultSet = ps.executeQuery();
        if (resultSet.next()){
            String name = resultSet.getString("name");
            int idBranches = resultSet.getInt("id");
            Address address = null;
            branch = new Branch(idBranches, name, address);
        }
        return branch;
    }

    @Override
    public int create(Branch branch) throws SQLException {
        return 0;
    }

    @Override
    public int update(Branch branch) throws SQLException {
        return 0;
    }

    @Override
    public int delete(int id) throws SQLException {
        return 0;
    }
}
