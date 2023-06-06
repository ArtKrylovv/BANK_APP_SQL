package com.solvd.bankapp.dao.daoMySQL;

import com.solvd.bankapp.dao.iCustomerDao;
import com.solvd.bankapp.dao.iEmployeeDao;
import com.solvd.bankapp.dao.iLoanApplicationDao;
import com.solvd.bankapp.dao.iLoanApprovalDao;
import com.solvd.bankapp.model.Customer;
import com.solvd.bankapp.model.Employee;
import com.solvd.bankapp.model.LoanApplication;
import com.solvd.bankapp.model.LoanApproval;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoanApplicationService {
    private iLoanApplicationDao loanDao = new LoanApplicationDaoImpl();
    private iCustomerDao customerDao = new CustomerDaoImpl();
    private iEmployeeDao employeeDao = new EmployeeDaoImpl();
    private iLoanApprovalDao loanApprovalDao = new LoanApprovalDaoImpl();

    public LoanApplication get(int id) throws SQLException {
        LoanApplication loanApplication = loanDao.get(id);
        Customer customer = customerDao.get(loanDao.getCustomerSsnByLoanId(id));
        Employee employee = employeeDao.get(loanDao.getEmployeeIdByLoanId(id));
        LoanApproval loanApproval = loanApprovalDao.getApprovalByApplicationId(id);
        loanApplication.setLoanApproval(loanApproval);
        loanApplication.setCustomer(customer);
        loanApplication.setEmployee(employee);
        return loanApplication;
    }

    public List<LoanApplication> getAll() throws SQLException {
        List<LoanApplication> loanApplicationsList = new ArrayList<>();
        for (LoanApplication loanApplication: loanDao.getAll()) {
            Customer customer = customerDao.get(loanDao.getCustomerSsnByLoanId(loanApplication.getId()));
            Employee employee = employeeDao.get(loanDao.getEmployeeIdByLoanId(loanApplication.getId()));
            LoanApproval loanApproval = loanApprovalDao.getApprovalByApplicationId(loanApplication.getId());
            loanApplication.setLoanApproval(loanApproval);
            loanApplication.setCustomer(customer);
            loanApplication.setEmployee(employee);
            loanApplicationsList.add(loanApplication);
        }
        return loanApplicationsList;
    }
}
