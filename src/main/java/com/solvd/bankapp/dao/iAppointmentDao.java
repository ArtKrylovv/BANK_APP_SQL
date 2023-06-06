package com.solvd.bankapp.dao;

import com.solvd.bankapp.model.Appointment;

import java.sql.SQLException;

public interface iAppointmentDao extends iDao<Appointment> {
    int getCustomerSsnByAppointmentId(int id) throws SQLException;
    int getEmployeeIdByAppointmentId(int id) throws SQLException;
}
