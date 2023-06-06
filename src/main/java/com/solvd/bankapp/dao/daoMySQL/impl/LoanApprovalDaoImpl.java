package com.solvd.bankapp.dao.daoMySQL.impl;

import com.solvd.bankapp.dao.iLoanApprovalDao;
import com.solvd.bankapp.model.Employee;
import com.solvd.bankapp.model.LoanApproval;
import com.solvd.bankapp.utils.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoanApprovalDaoImpl implements iLoanApprovalDao {
    @Override
    public int getEmployeeIdByApprovalId(int id) throws SQLException {
        int employee_id = 0;
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        String sql = "SELECT employees_id FROM Loan_approvals WHERE id =?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet resultSet = ps.executeQuery();
        if (resultSet.next()){
            employee_id = resultSet.getInt("employees_id");
        }
        connectionPool.releaseConnection(connection);
        return employee_id;
    }

    @Override
    public LoanApproval getApprovalByApplicationId(int id) throws SQLException {
       LoanApproval loanApproval = null;
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        String sql = "SELECT id, Approval_status, Employees_Id FROM Loan_approvals WHERE loan_applications_id =?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet resultSet = ps.executeQuery();
        if (resultSet.next()) {
            int approvalId = resultSet.getInt("id");
            boolean approvalStatus = resultSet.getBoolean("Approval_status");
            Employee employee = null;
            loanApproval = new LoanApproval(approvalId, approvalStatus, id,employee);
        }
        connectionPool.releaseConnection(connection);
        return loanApproval;
    }

    @Override
    public List<LoanApproval> getAll() throws SQLException {
        List<LoanApproval> approvalsList = new ArrayList<>();
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        String sql = "SELECT id, Approval_status, Loan_applications_id, Employees_Id FROM Loan_approvals";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            int applicationsId = resultSet.getInt("Loan_applications_id");
            boolean approvalStatus = resultSet.getBoolean("Approval_status");
            Employee employee = null;
            approvalsList.add(new LoanApproval(id, approvalStatus, applicationsId,employee));
        }
        connectionPool.releaseConnection(connection);
        return approvalsList;
    }

    @Override
    public LoanApproval get(int id) throws SQLException {
        LoanApproval loanApproval = null;
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        String sql = "SELECT id, Approval_status, Loan_applications_id, Employees_Id FROM Loan_approvals WHERE id =?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1,id);
        ResultSet resultSet = ps.executeQuery();
        if (resultSet.next()) {
            int applicationsId = resultSet.getInt("Loan_applications_id");
            boolean approvalStatus = resultSet.getBoolean("Approval_status");
            Employee employee = null;
            loanApproval = new LoanApproval(id, approvalStatus, applicationsId,employee);
        }
        connectionPool.releaseConnection(connection);
        return loanApproval;
    }

    @Override
    public int create(LoanApproval loanApproval) throws SQLException {
        return 0;
    }

    @Override
    public int update(LoanApproval loanApproval) throws SQLException {
        return 0;
    }

    @Override
    public int delete(int id) throws SQLException {
        return 0;
    }
}
