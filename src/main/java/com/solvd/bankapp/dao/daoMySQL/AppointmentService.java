package com.solvd.bankapp.dao.daoMySQL;

import com.solvd.bankapp.dao.iAppointmentDao;
import com.solvd.bankapp.dao.iCustomerDao;
import com.solvd.bankapp.dao.iEmployeeDao;
import com.solvd.bankapp.model.Appointment;
import com.solvd.bankapp.model.Customer;
import com.solvd.bankapp.model.Employee;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AppointmentService {
    private iAppointmentDao appointmentDao = new AppointmentDaoImpl();
    private iCustomerDao customerDao = new CustomerDaoImpl();
    private iEmployeeDao employeeDao = new EmployeeDaoImpl();

    public Appointment get(int id) throws SQLException {
        Appointment appointment = appointmentDao.get(id);
        Customer customer = customerDao.get(appointmentDao.getCustomerSsnByAppointmentId(id));
        Employee employee = employeeDao.get(appointmentDao.getEmployeeIdByAppointmentId(id));
        appointment.setCustomer(customer);
        appointment.setEmployee(employee);
        return appointment;
    }

    public List<Appointment> getAll() throws SQLException {
        List<Appointment> appointmentsList = new ArrayList<>();
        for (Appointment appointment: appointmentDao.getAll()) {
            Customer customer = customerDao.get(appointmentDao.getCustomerSsnByAppointmentId(appointment.getId()));
            Employee employee = employeeDao.get(appointmentDao.getEmployeeIdByAppointmentId(appointment.getId()));
            appointment.setCustomer(customer);
            appointment.setEmployee(employee);
            appointmentsList.add(appointment);
        }
        return appointmentsList;
    }
}
