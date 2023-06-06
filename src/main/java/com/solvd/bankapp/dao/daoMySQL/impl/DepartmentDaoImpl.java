package com.solvd.bankapp.dao.daoMySQL.impl;

import com.solvd.bankapp.dao.iDepartmentDao;
import com.solvd.bankapp.model.Department;
import com.solvd.bankapp.utils.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoImpl implements iDepartmentDao {

    @Override
    public List<Department> getAll() throws SQLException {
        List<Department> departmentsList = new ArrayList<>();
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();

        String sql = "SELECT id, name FROM Departments";
        PreparedStatement ps =  connection.prepareStatement(sql);
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()){
            int departmentId = resultSet.getInt("id");
            String departmentName = resultSet.getString("name");
            departmentsList.add(new Department(departmentId, departmentName));
        }
        connectionPool.releaseConnection(connection);
        return departmentsList;
    }

    @Override
    public Department get(int id) throws SQLException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        Department department = null;
        String sql = "SELECT id, name FROM departments WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()){
            int departmentId = rs.getInt("id");
            String name = rs.getString("name");
            department = new Department(departmentId, name);
        }
        connectionPool.releaseConnection(connection);
        return department;
    }

    @Override
    public int create(Department department) throws SQLException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        String sql = "INSERT INTO departments(name) VALUES (?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, department.getName());
        int rs = ps.executeUpdate();
        ps.close();
        connectionPool.releaseConnection(connection);
        return rs;
    }

    @Override
    public int update(Department department) throws SQLException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        String sql = "UPDATE Departments SET Name = ? WHERE Id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, department.getName());
        ps.setInt(2, department.getId());
        int rs = ps.executeUpdate();
        ps.close();
        connectionPool.releaseConnection(connection);
        return rs;
    }

    @Override
    public int delete(int id) throws SQLException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        String sql = "DELETE FROM Departments WHERE Id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        int rs = ps.executeUpdate();
        ps.close();
        connectionPool.releaseConnection(connection);
        return rs;
    }
}