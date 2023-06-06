package com.solvd.bankapp.dao.daoMySQL;

import com.solvd.bankapp.dao.iLoanApplicationDao;
import com.solvd.bankapp.model.Customer;
import com.solvd.bankapp.model.Employee;
import com.solvd.bankapp.model.LoanApplication;
import com.solvd.bankapp.model.LoanApproval;
import com.solvd.bankapp.utils.ConnectionUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoanApplicationDaoImpl implements iLoanApplicationDao {
    @Override
    public int getCustomerSsnByLoanId(int id) throws SQLException {
        int ssn = 0;
        Connection connection = ConnectionUtils.getConnection();
        String sql = "SELECT customers_id FROM loan_applications WHERE id =?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet resultSet = ps.executeQuery();
        if (resultSet.next()){
            ssn = resultSet.getInt("customers_id");
        }
        return ssn;
    }

    @Override
    public int getEmployeeIdByLoanId(int id) throws SQLException {
        int employeesId = 0;
        Connection connection = ConnectionUtils.getConnection();
        String sql = "SELECT employees_id FROM loan_applications WHERE id =?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet resultSet = ps.executeQuery();
        if (resultSet.next()){
            employeesId = resultSet.getInt("employees_id");
        }
        return employeesId;
    }

    @Override
    public List<LoanApplication> getAll() throws SQLException {
        List<LoanApplication> loanApplicationsList = new ArrayList<>();
        Connection connection = ConnectionUtils.getConnection();
        String sql = "SELECT id,Object, Amount, Interest, Duration, Created, Employees_Id, Customers_Id FROM Loan_applications";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String object = resultSet.getString("object");
            int amount = resultSet.getInt("amount");
            double interest = resultSet.getDouble("interest");
            int duration = resultSet.getInt("duration");
            String dateCreated = resultSet.getString("created");
            Employee employee = null;
            Customer customer = null;
            LoanApproval loanApproval = null;
            loanApplicationsList.add(new LoanApplication(id, object, amount, interest, duration, dateCreated, employee, customer, loanApproval));
        }
        return loanApplicationsList;
    }

    @Override
    public LoanApplication get(int id) throws SQLException {
        LoanApplication loanApplication = null;
        Connection connection = ConnectionUtils.getConnection();
        String sql = "SELECT Object, Amount, Interest, Duration, Created, Employees_Id, Customers_Id FROM Loan_applications WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet resultSet = ps.executeQuery();
        if (resultSet.next()) {
            String object = resultSet.getString("object");
            int amount = resultSet.getInt("amount");
            double interest = resultSet.getDouble("interest");
            int duration = resultSet.getInt("duration");
            String dateCreated = resultSet.getString("created");
            Employee employee = null;
            Customer customer = null;
            LoanApproval loanApproval = null;
            loanApplication = new LoanApplication(id, object, amount, interest, duration, dateCreated, employee, customer, loanApproval);
        }
        return loanApplication;
    }

    @Override
    public int create(LoanApplication loanApplication) throws SQLException {
        return 0;
    }

    @Override
    public int update(LoanApplication loanApplication) throws SQLException {
        return 0;
    }

    @Override
    public int delete(int id) throws SQLException {
        return 0;
    }
}
