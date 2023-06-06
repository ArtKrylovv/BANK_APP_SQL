package com.solvd.bankapp.dao.daoMySQL.impl;

import com.solvd.bankapp.dao.iAddressDao;
import com.solvd.bankapp.model.Address;
import com.solvd.bankapp.model.State;
import com.solvd.bankapp.utils.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddressDaoImpl implements iAddressDao {
    @Override
    public List<Address> getAll() throws SQLException {
        List<Address> addressesList = new ArrayList<>();
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        String sql = "SELECT id, house_number, street_name, apt_number, city, state_Id FROM Addresses";
        PreparedStatement ps =  connection.prepareStatement(sql);
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()){
            int idAddress = resultSet.getInt("id");
            int houseNumber = resultSet.getInt("house_number");
            String streetName = resultSet.getString("street_name");
            int aptNumber = resultSet.getInt("apt_number");
            String city = resultSet.getString("city");
            State state = null;
            addressesList.add(new Address(idAddress, houseNumber,streetName, aptNumber,city,state));
        }
        connectionPool.releaseConnection(connection);
        return addressesList;
    }

    @Override
    public Address get(int id) throws SQLException {
        Address address = null;
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        String sql = "SELECT id, house_number, street_name, apt_number, city, state_Id FROM Addresses WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet resultSet = ps.executeQuery();
        if (resultSet.next()){
            int idAddress = resultSet.getInt("id");
            int houseNumber = resultSet.getInt("house_number");
            String streetName = resultSet.getString("street_name");
            int aptNumber = resultSet.getInt("apt_number");
            String city = resultSet.getString("city");
            State state = null;
            address = new Address(idAddress, houseNumber,streetName, aptNumber,city,state);
        }
        connectionPool.releaseConnection(connection);
        return address;
    }

    @Override
    public int create(Address address) throws SQLException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        String sql = "INSERT INTO Addresses (House_number, Street_name, Apt_number,City, State_Id) VALUES (?,?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, address.getHouseNumber());
        ps.setString(2, address.getStreetName());
        ps.setInt(3, address.getAptNumber());
        ps.setString(4, address.getCity());
        ps.setInt(5, address.getState().getId());
        int rs = ps.executeUpdate();
        ps.close();
        connectionPool.releaseConnection(connection);
        return rs;
    }

    @Override
    public int update(Address address) throws SQLException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();

        String sql = "UPDATE Addresses SET House_number=?, Street_name=?, Apt_number=?,City=? WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, address.getHouseNumber());
        ps.setString(2, address.getStreetName());
        ps.setInt(3, address.getAptNumber());
        ps.setString(4, address.getCity());
        ps.setInt(5, address.getState().getId());
        ps.setInt(5, address.getState().getId());
        int rs = ps.executeUpdate();
        ps.close();
        connectionPool.releaseConnection(connection);
        return rs;
    }

    @Override
    public int delete(int id) throws SQLException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        String sql = "DELETE FROM Addresses WHERE Id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        int rs = ps.executeUpdate();
        ps.close();
        connectionPool.releaseConnection(connection);
        return rs;
    }

    @Override
    public int getStateIdByAddressId(int id) throws SQLException {
        int stateId = 0;
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        String sql = "SELECT state_id FROM Addresses WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet resultSet = ps.executeQuery();
        if (resultSet.next()){
            stateId = resultSet.getInt("state_id");
        }
        connectionPool.releaseConnection(connection);
        return stateId;
    }
}
