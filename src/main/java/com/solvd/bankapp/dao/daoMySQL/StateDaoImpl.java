package com.solvd.bankapp.dao.daoMySQL;

import com.solvd.bankapp.dao.iStateDao;
import com.solvd.bankapp.model.State;
import com.solvd.bankapp.utils.ConnectionUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StateDaoImpl implements iStateDao {

    @Override
    public List<State> getAll() throws SQLException {
        List<State> statesList = new ArrayList<>();
        Connection connection = ConnectionUtils.getConnection();
        String sql = "SELECT id, abbreviation, name FROM States";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String abbreviation = resultSet.getString("abbreviation");
            String name = resultSet.getString("name");
            statesList.add(new State(id, abbreviation,name));
        }
        return statesList;
    }

    @Override
    public State get(int id) throws SQLException {
        State state = null;
        Connection connection = ConnectionUtils.getConnection();
        String sql = "SELECT id, abbreviation, name FROM States WHERE id =?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet resultSet = ps.executeQuery();
        if (resultSet.next()){
            int idState = resultSet.getInt("id");
            String abbreviation = resultSet.getString("abbreviation");
            String name = resultSet.getString("name");
            state = new State(idState,abbreviation,name);

        }
        return state;
    }

    @Override
    public int create(State state) throws SQLException {
        Connection connection = ConnectionUtils.getConnection();
        String sql = "INSERT INTO States (abbreviation, name) VALUES (?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, state.getAbbreviation());
        ps.setString(2, state.getName());
        int rs = ps.executeUpdate();
        ps.close();
        connection.close();
        return rs;
    }

    @Override
    public int update(State state) throws SQLException {
        Connection connection = ConnectionUtils.getConnection();
        String sql = "UPDATE States SET abbreviation = ?, name = ? WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, state.getAbbreviation());
        ps.setString(2, state.getName());
        ps.setInt(3, state.getId());

        int rs = ps.executeUpdate();
        ps.close();
        connection.close();
        return rs;
    }

    @Override
    public int delete(int id) throws SQLException {
        Connection connection = ConnectionUtils.getConnection();
        String sql = "DELETE FROM States WHERE Id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        int rs = ps.executeUpdate();
        ps.close();
        connection.close();
        return rs;
    }
}
