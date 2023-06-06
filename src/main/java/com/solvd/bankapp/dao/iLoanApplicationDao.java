package com.solvd.bankapp.dao;

import com.solvd.bankapp.model.LoanApplication;

import java.sql.SQLException;

public interface iLoanApplicationDao extends iDao<LoanApplication> {
    int getCustomerSsnByLoanId(int id) throws SQLException;
    int getEmployeeIdByLoanId(int id) throws SQLException;
}
