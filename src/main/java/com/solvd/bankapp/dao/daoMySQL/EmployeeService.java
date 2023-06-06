package com.solvd.bankapp.dao.daoMySQL;

import com.solvd.bankapp.dao.*;
import com.solvd.bankapp.model.Address;
import com.solvd.bankapp.model.Employee;
import com.solvd.bankapp.model.State;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeService {
    private iEmployeeDao employeeDao = new EmployeeDaoImpl();
    private iAddressDao addressDao = new AddressDaoImpl();
    private iDepartmentDao departmentDao = new DepartmentDaoImpl();
    private iBranchDao branchDao = new BranchDaoImpl();
    private iPayrollDao payrollDao = new PayrollDaoImpl();

    public Employee get(int id) throws SQLException  {
        Employee employee = employeeDao.get(id);
        int addressId = employeeDao.getAddressIdByEmployeeId(id);
        int departmentId = employeeDao.getDepartmentIdByEmployeeId(id);
        int branchId = employeeDao.getBranchIdByEmployeeId(id);
        int payrollId = employeeDao.getPayrollIdByEmployeeId(id);

        employee.setAddress(addressDao.get(addressId));
        employee.setDepartment(departmentDao.get(departmentId));
        employee.setBranch(branchDao.get(branchId));
        employee.setPayroll(payrollDao.get(payrollId));
        return employee;
    }

    public List<Employee> getAll() throws SQLException  {
        List<Employee> employeesList = new ArrayList<>();
        for (Employee employee: employeeDao.getAll()) {

            int addressId = employeeDao.getAddressIdByEmployeeId(employee.getId());
            int departmentId = employeeDao.getDepartmentIdByEmployeeId(employee.getId());
            int branchId = employeeDao.getBranchIdByEmployeeId(employee.getId());
            int payrollId = employeeDao.getPayrollIdByEmployeeId(employee.getId());

            employee.setAddress(addressDao.get(addressId));
            employee.setDepartment(departmentDao.get(departmentId));
            employee.setBranch(branchDao.get(branchId));
            employee.setPayroll(payrollDao.get(payrollId));

            employeesList.add(employee);
    }
        return employeesList;
    }
}
