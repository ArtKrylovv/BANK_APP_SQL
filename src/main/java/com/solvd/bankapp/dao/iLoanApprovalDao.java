package com.solvd.bankapp.dao;

import com.solvd.bankapp.model.LoanApproval;

import java.sql.SQLException;

public interface iLoanApprovalDao extends iDao<LoanApproval> {
    int getEmployeeIdByApprovalId(int id) throws SQLException;
    LoanApproval getApprovalByApplicationId(int id) throws SQLException;
}
