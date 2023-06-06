package com.solvd.bankapp.dao.daoMySQL.impl;

import com.solvd.bankapp.dao.iEmployeeDao;
import com.solvd.bankapp.model.*;
import com.solvd.bankapp.utils.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl implements iEmployeeDao {

    @Override
    public List<Employee> getAll() throws SQLException {
        List<Employee> employeesList = new ArrayList<>();
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();

        String sql = "SELECT First_name, Last_name, Role, Addresses_Id, Departments_Id, Branches_Id, Payrolls_Id, id FROM Employees";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()) {
            String firstName = resultSet.getString("First_name");
            String lastName = resultSet.getString("Last_name");
            String role = resultSet.getString("role");
            int id = resultSet.getInt("id");
            Address address = null;
            Department department = null;
            Branch branch = null;
            Payroll payroll = null;
            employeesList.add(new Employee(id, firstName, lastName, role, address, department, branch, payroll));
        }
        connectionPool.releaseConnection(connection);
        return employeesList;
    }

    @Override
    public Employee get(int id) throws SQLException {
        Employee employee = null;
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        String sql = "SELECT First_name, Last_name, Role, Addresses_Id, Departments_Id, Branches_Id, Payrolls_Id FROM Employees WHERE id =?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet resultSet = ps.executeQuery();
        if (resultSet.next()) {
            String firstName = resultSet.getString("First_name");
            String lastName = resultSet.getString("Last_name");
            String role = resultSet.getString("role");
            Address address = null;
            Department department = null;
            Branch branch = null;
            Payroll payroll = null;
            employee = new Employee(id, firstName, lastName, role, address, department, branch, payroll);
        }
        connectionPool.releaseConnection(connection);
        return employee;
    }

    @Override
    public int create(Employee employee) throws SQLException {
        return 0;
    }

    @Override
    public int update(Employee employee) throws SQLException {
        return 0;
    }

    @Override
    public int delete(int id) throws SQLException {
        return 0;
    }

    @Override
    public int getDepartmentIdByEmployeeId(int id) throws SQLException {
        int departmentId = 0;
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();

        String sql = "SELECT departments_id FROM Employees WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet resultSet = ps.executeQuery();
        if (resultSet.next()) {
            departmentId = resultSet.getInt("departments_id");
        }
        connectionPool.releaseConnection(connection);
        return departmentId;
    }


    @Override
    public int getBranchIdByEmployeeId(int id) throws SQLException {
        int branchId = 0;
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        String sql = "SELECT branches_id FROM Employees WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet resultSet = ps.executeQuery();
        if (resultSet.next()) {
            branchId = resultSet.getInt("branches_id");
        }
        connectionPool.releaseConnection(connection);
        return branchId;
    }

    @Override
    public int getAddressIdByEmployeeId(int id) throws SQLException {
        int addressId = 0;
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        String sql = "SELECT Addresses_Id FROM Employees WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet resultSet = ps.executeQuery();
        if (resultSet.next()) {
            addressId = resultSet.getInt("Addresses_Id");
        }
        connectionPool.releaseConnection(connection);
        return addressId;
    }

    @Override
    public int getPayrollIdByEmployeeId(int id) throws SQLException {
        int employeeId = 0;
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        String sql = "SELECT payrolls_id FROM Employees WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet resultSet = ps.executeQuery();
        if (resultSet.next()) {
            employeeId = resultSet.getInt("payrolls_id");
        }
        connectionPool.releaseConnection(connection);
        return employeeId;
    }
}
