package com.solvd.bankapp.dao.daoMySQL.services;

import com.solvd.bankapp.dao.daoMySQL.impl.EmployeeDaoImpl;
import com.solvd.bankapp.dao.daoMySQL.impl.LoanApprovalDaoImpl;
import com.solvd.bankapp.dao.iLoanApprovalDao;
import com.solvd.bankapp.dao.iEmployeeDao;
import com.solvd.bankapp.model.Employee;
import com.solvd.bankapp.model.LoanApproval;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoanApprovalService {
    private iLoanApprovalDao approvalDao = new LoanApprovalDaoImpl();
    private iEmployeeDao employeeDao = new EmployeeDaoImpl();

    public LoanApproval get(int id) throws SQLException {
        LoanApproval loanApproval = approvalDao.get(id);
        Employee employee = employeeDao.get(approvalDao.getEmployeeIdByApprovalId(id));
        loanApproval.setEmployee(employee);
        return loanApproval;
    }

    public List<LoanApproval> getAll() throws SQLException {
        List<LoanApproval> approvalsList = new ArrayList<>();
        for (LoanApproval loanApproval: approvalDao.getAll()) {
            Employee employee = employeeDao.get(approvalDao.getEmployeeIdByApprovalId(loanApproval.getId()));
            loanApproval.setEmployee(employee);
            approvalsList.add(loanApproval);
        }
        return approvalsList;
    }
}
