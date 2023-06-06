package com.solvd.bankapp.dao.daoMySQL.impl;

import com.solvd.bankapp.dao.iAppointmentDao;
import com.solvd.bankapp.model.Appointment;
import com.solvd.bankapp.model.Customer;
import com.solvd.bankapp.model.Employee;
import com.solvd.bankapp.utils.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDaoImpl implements iAppointmentDao {

    @Override
    public int getCustomerSsnByAppointmentId(int id) throws SQLException {
        int ssn = 0;
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        String sql = "SELECT customers_id FROM appointments WHERE id =?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet resultSet = ps.executeQuery();
        if (resultSet.next()){
            ssn = resultSet.getInt("customers_id");
        }
        connectionPool.releaseConnection(connection);
        return ssn;
    }

    @Override
    public int getEmployeeIdByAppointmentId(int id) throws SQLException {
        int employeesId = 0;
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        String sql = "SELECT employees_id FROM appointments WHERE id =?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet resultSet = ps.executeQuery();
        if (resultSet.next()) {
            employeesId = resultSet.getInt("employees_id");
        }
        connectionPool.releaseConnection(connection);
        return employeesId;
    }

    @Override
    public List<Appointment> getAll() throws SQLException {
        List<Appointment> appointmentsList = new ArrayList<>();
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        String sql = "SELECT id, Reason, Completed, Employees_Id, Customers_Id FROM Appointments";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String reason = resultSet.getString("reason");
            boolean completed = resultSet.getBoolean("completed");
            Employee employee = null;
            Customer customer = null;
            appointmentsList.add(new Appointment(id, reason,completed,employee, customer));
        }
        connectionPool.releaseConnection(connection);
        return appointmentsList;
    }



    @Override
    public Appointment get(int id) throws SQLException {
        Appointment appointment = null;
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        String sql = "SELECT Reason, Completed, Employees_Id, Customers_Id FROM Appointments WHERE id =? ";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet resultSet = ps.executeQuery();
        if (resultSet.next()) {
            String reason = resultSet.getString("reason");
            boolean completed = resultSet.getBoolean("completed");
            Employee employee = null;
            Customer customer = null;
            appointment = new Appointment(id, reason, completed, employee, customer);
        }
        connectionPool.releaseConnection(connection);
        return appointment;
    }

    @Override
    public int create(Appointment appointment) throws SQLException {
        return 0;
    }

    @Override
    public int update(Appointment appointment) throws SQLException {
        return 0;
    }

    @Override
    public int delete(int id) throws SQLException {
        return 0;
    }
}
