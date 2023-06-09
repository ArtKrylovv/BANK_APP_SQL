package com.solvd.bankapp.dao.implMySQL.impl;

import com.solvd.bankapp.dao.IStateDao;
import com.solvd.bankapp.bin.State;
import com.solvd.bankapp.utils.db.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StateDaoImpl implements IStateDao {
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final Logger LOGGER = LogManager.getLogger(StateDaoImpl.class);
    private static final String sqlGetAll = "SELECT id, abbreviation, name FROM States";
    private static final String sqlGetById = "SELECT id, abbreviation, name FROM States WHERE id =?";
    private static final String sqlCreate = "INSERT INTO States (abbreviation, name) VALUES (?, ?)";
    private static final String sqlUpdate = "UPDATE States SET abbreviation = ?, name = ? WHERE id = ?";
    private static final String sqlDelete = "DELETE FROM States WHERE Id = ?";

    @Override
    public List<State> getAll() {
        List<State> statesList = new ArrayList<>();
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(sqlGetAll);
             ResultSet resultSet = ps.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String abbreviation = resultSet.getString("abbreviation");
                String name = resultSet.getString("name");
                statesList.add(new State(id, abbreviation, name));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return statesList;
    }

    @Override
    public State get(int id) {
        State state = null;
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(sqlGetById)) {
            ps.setInt(1, id);
            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    int idState = resultSet.getInt("id");
                    String abbreviation = resultSet.getString("abbreviation");
                    String name = resultSet.getString("name");
                    state = new State(idState, abbreviation, name);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return state;
    }

    @Override
    public int create(State state) {
        Connection connection = connectionPool.getConnection();
        int rs = 0;
        try (PreparedStatement ps = connection.prepareStatement(sqlCreate)){
            ps.setString(1, state.getAbbreviation());
            ps.setString(2, state.getName());
            rs = ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return rs;
    }

    @Override
    public int update(State state){
        Connection connection = connectionPool.getConnection();
        int rs = 0;
        try (PreparedStatement ps = connection.prepareStatement(sqlUpdate)){
            ps.setString(1, state.getAbbreviation());
            ps.setString(2, state.getName());
            ps.setInt(3, state.getId());
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
}

