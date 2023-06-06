package com.solvd.bankapp;

import com.solvd.bankapp.dao.daoMySQL.impl.CustomerDaoImpl;
import com.solvd.bankapp.dao.daoMySQL.impl.PayrollDaoImpl;
import com.solvd.bankapp.dao.daoMySQL.services.LoanApplicationService;
import com.solvd.bankapp.model.Customer;
import com.solvd.bankapp.utils.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main (String[] args) throws SQLException {
        PayrollDaoImpl payrollDao = new PayrollDaoImpl();
        System.out.println(payrollDao.getAll());
    }
}
