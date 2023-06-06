package com.solvd.bankapp.dao;

import com.solvd.bankapp.model.Department;
import com.solvd.bankapp.model.Employee;

import java.sql.SQLException;

public interface iEmployeeDao extends iDao<Employee> {
    int getDepartmentIdByEmployeeId(int id) throws SQLException;
    int getBranchIdByEmployeeId(int id) throws SQLException;
    int getAddressIdByEmployeeId(int id)throws SQLException;
    int getPayrollIdByEmployeeId(int id)throws SQLException;

}
