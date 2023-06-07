package com.solvd.bankapp.dao.daoMySQL.impl;

import com.solvd.bankapp.dao.iAddressDao;
import com.solvd.bankapp.model.Address;
import com.solvd.bankapp.model.State;
import com.solvd.bankapp.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AddressDaoImpl implements iAddressDao {
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final String sqlGetAll = "SELECT id, house_number, street_name, apt_number, city, state_Id FROM Addresses";
    private static final String sqlGetById = "SELECT id, house_number, street_name, apt_number, city, state_Id FROM Addresses WHERE id = ?";
    private static final String sqlGetStateIdByAddressId = "SELECT state_id FROM Addresses WHERE id = ?";
    private static final String sqlCreate = "INSERT INTO Addresses (House_number, Street_name, Apt_number,City, State_Id, id) VALUES (?,?,?,?,?,?)";
    private static final String sqlUpdate = "UPDATE Addresses SET House_number=?, Street_name=?, Apt_number=?,City=?, State_id=? WHERE id = ?";
    private static final String sqlDelete = "DELETE FROM Addresses WHERE Id = ?";
    private static final Logger LOGGER = LogManager.getLogger(AddressDaoImpl.class);

    @Override
    public List<Address> getAll() {
        List<Address> addressesList = new ArrayList<>();
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(sqlGetAll);
             ResultSet resultSet = ps.executeQuery()) {
            while (resultSet.next()) {
                int idAddress = resultSet.getInt("id");
                int houseNumber = resultSet.getInt("house_number");
                String streetName = resultSet.getString("street_name");
                int aptNumber = resultSet.getInt("apt_number");
                String city = resultSet.getString("city");
                State state = null;
                addressesList.add(new Address(idAddress, houseNumber, streetName, aptNumber, city, state));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return addressesList;
    }

    @Override
    public Address get(int id) {
        Address address = null;
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(sqlGetById)) {
            ps.setInt(1, id);
            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    int idAddress = resultSet.getInt("id");
                    int houseNumber = resultSet.getInt("house_number");
                    String streetName = resultSet.getString("street_name");
                    int aptNumber = resultSet.getInt("apt_number");
                    String city = resultSet.getString("city");
                    State state = null;
                    address = new Address(idAddress, houseNumber, streetName, aptNumber, city, state);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return address;
    }

    @Override
    public int create(Address address) {
        Connection connection = connectionPool.getConnection();
        int rs = 0;
        try (PreparedStatement ps = connection.prepareStatement(sqlCreate)) {
            ps.setInt(1, address.getHouseNumber());
            ps.setString(2, address.getStreetName());
            ps.setInt(3, address.getAptNumber());
            ps.setString(4, address.getCity());
            ps.setInt(5, address.getState().getId());
            ps.setInt(6, address.getId());
            rs = ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return rs;
    }

    @Override
    public int update(Address address) throws SQLException {
        Connection connection = connectionPool.getConnection();
        int rs = 0;
        try (PreparedStatement ps = connection.prepareStatement(sqlUpdate)) {
            ps.setInt(1, address.getHouseNumber());
            ps.setString(2, address.getStreetName());
            ps.setInt(3, address.getAptNumber());
            ps.setString(4, address.getCity());
            ps.setInt(5, address.getState().getId());
            ps.setInt(6, address.getId());

            rs = ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return rs;
    }

    @Override
    public int delete(int id) {
        Connection connection = connectionPool.getConnection();
        int rs = 0;
        try (PreparedStatement ps = connection.prepareStatement(sqlDelete)) {
            ps.setInt(1, id);
            rs = ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return rs;
    }

    @Override
    public int getStateIdByAddressId(int id) {
        Connection connection = connectionPool.getConnection();
        int stateId = 0;
        try (PreparedStatement ps = connection.prepareStatement(sqlGetStateIdByAddressId);
        ) {
            ps.setInt(1, id);
            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    stateId = resultSet.getInt("state_id");
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return stateId;
    }
}
