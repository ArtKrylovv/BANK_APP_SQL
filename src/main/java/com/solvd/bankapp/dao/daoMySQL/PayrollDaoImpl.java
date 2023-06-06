package com.solvd.bankapp.dao.daoMySQL;

import com.solvd.bankapp.dao.iDao;
import com.solvd.bankapp.dao.iPayrollDao;
import com.solvd.bankapp.model.Address;
import com.solvd.bankapp.model.Branch;
import com.solvd.bankapp.model.PayType;
import com.solvd.bankapp.model.Payroll;
import com.solvd.bankapp.utils.ConnectionUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PayrollDaoImpl implements iPayrollDao {
    @Override
    public List<Payroll> getAll() throws SQLException {
        List<Payroll> payrollsList = new ArrayList<>();
        Connection connection = ConnectionUtils.getConnection();
        String sql = "SELECT id, Pay_type, Hourly_rate, Annual_salary FROM Payrolls";
        PreparedStatement ps =  connection.prepareStatement(sql);
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()){
            int payrollId = resultSet.getInt("id");
            int hourlyRate = resultSet.getInt("Hourly_rate");
            int annualSalary = resultSet.getInt("Annual_salary");
            String payTypeTemp = resultSet.getString("Pay_type");
            // create and reuse utility method
            PayType payType = null;
            if (payTypeTemp.equals("Full-time")) {
                payType = PayType.FULL_TIME;
            } else if (payTypeTemp.equals("Part-time")) {
                payType = PayType.PART_TIME;
            }
            payrollsList.add(new Payroll(payrollId, payType, annualSalary, hourlyRate));
        }
        return payrollsList;
    }

    @Override
    public Payroll get(int id) throws SQLException {
        Connection connection = ConnectionUtils.getConnection();
        String sql = "SELECT id, Pay_type, Hourly_rate, Annual_salary FROM Payrolls WHERE id =?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1,id);
        ResultSet resultSet = ps.executeQuery();
        Payroll payRoll = null;
        if (resultSet.next()) {
            int payrollId = resultSet.getInt("id");
            int hourlyRate = resultSet.getInt("Hourly_rate");
            int annualSalary = resultSet.getInt("Annual_salary");
            String payTypeTemp = resultSet.getString("Pay_type");
            // create and reuse utility method
            PayType payType = null;
            if (payTypeTemp.equals("Full-time")) {
                payType = PayType.FULL_TIME;
            } else if (payTypeTemp.equals("Part-time")) {
                payType = PayType.PART_TIME;
            }
            payRoll = new Payroll(payrollId, payType, annualSalary, hourlyRate);
        }
        return payRoll;
    }

    @Override
    public int create(Payroll payroll) throws SQLException {
        Connection connection = ConnectionUtils.getConnection();
        String sql = "INSERT INTO Payrolls (Pay_type, Hourly_rate, Annual_salary) VALUES (?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(2, payroll.getHourlyRate());
        ps.setInt(3, payroll.getAnnualSalary());

        String payType = null;
        // create and reuse utility method
        if (payroll.getPayType().name().equals("FULL_TIME")) {
            payType = "Full-time";
        } else if (payroll.getPayType().name().equals("PART_TIME")) {
            payType = "Part-time";
        }
        ps.setString(1, payType);
        int rs = ps.executeUpdate();
        ps.close();
        connection.close();
        return rs;
    }

    @Override
    public int update(Payroll payroll) throws SQLException {
        Connection connection = ConnectionUtils.getConnection();
        String sql = "UPDATE Payrolls SET Pay_type=?, Hourly_rate=?, Annual_salary=? WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(2, payroll.getHourlyRate());
        ps.setInt(3, payroll.getAnnualSalary());
        ps.setInt(4, payroll.getId());

        String payType = null;
        // create and reuse utility method
        if (payroll.getPayType().name().equals("FULL_TIME")) {
            payType = "Full-time";
        } else if (payroll.getPayType().name().equals("PART_TIME")) {
            payType = "Part-time";
        }
        ps.setString(1, payType);
        int rs = ps.executeUpdate();
        ps.close();
        connection.close();
        return rs;
    }

    @Override
    public int delete(int id) throws SQLException {
        Connection connection = ConnectionUtils.getConnection();
        String sql = "DELETE FROM Payrolls WHERE Id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        int rs = ps.executeUpdate();
        ps.close();
        connection.close();
        return rs;
    }
}
