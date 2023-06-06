package com.solvd.bankapp;

import com.solvd.bankapp.dao.daoMySQL.*;

import java.sql.SQLException;

public class Main {
    public static <States> void main (String[] args) throws SQLException {
        // SELECT / READ
//        DepartmentDAOImpl depDao = new DepartmentDAOImpl();
//        Department department = depDao.get(3);
//        System.out.println(department);

        // SELECT / READ ALL
//        List<Department> depList = depDao.getAll();
//        System.out.println(depList);

//      INSERT / CREATE
//        Department department1 = new Department(0, "Asset protection");
//        int result = depDao.create(department1);
//        System.out.println(result);

        // UPDATE
//        Department department2 = new Department(7, "Assets protection");
//        int result2 = depDao.update(department1);
//        System.out.println(result2);

//        Department department3 = depDao.get(7);
//        System.out.println(department3);

        // DELETE
//        Department department4 = depDao.get(8);
//        System.out.println(department4);
//        int result3 = depDao.delete(department4);
//        System.out.println(result3);

//        StateDaoImpl stateDAO = new StateDaoImpl();
//        List<State> states = stateDAO.getAll();
//        System.out.println(states);
//
//        AddressDaoImpl addressDAO = new AddressDaoImpl();
//        System.out.println(addressDAO.get(2));
//
//        System.out.println(new AddressService().get(12));
////        System.out.println(addressDAO.getAll());
//
////        System.out.println(new AddressService().getAll());
//
//
//        stateDAO.delete(6);
//        System.out.println(stateDAO.getAll());

//        SavingsDaoImpl savingsDao = new SavingsDaoImpl();
//        System.out.println(savingsDao.getAll());
//
//        CustomerDaoImpl customerDao = new CustomerDaoImpl();
//        System.out.println(customerDao.getAll());
//
//        System.out.println(new CustomerService().get(100000000));
//        System.out.println(new CustomerService().getAll());

//        BranchDaoImpl branchDao = new BranchDaoImpl();
//        System.out.println(branchDao.getAll());
        System.out.println(new LoanApplicationService().get(1));
        System.out.println(new LoanApplicationService().getAll());
    }
}
