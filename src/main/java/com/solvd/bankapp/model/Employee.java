package com.solvd.bankapp.model;

public class Employee {
    private int id;
    private String firstName;
    private String lastName;
    private String role;
    private int addressId;
    private int departmentId;
    private int branchId;
    private int payrollId;

    public Employee(String firstName, String lastName, String role, int addressesId, int departmentsId, int brancheId, int payrollsId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.addressId = addressesId;
        this.departmentId = departmentsId;
        this.branchId = brancheId;
        this.payrollId = payrollsId;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getRole() {
        return role;
    }

    public int getAddressId() {
        return addressId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public int getBranchId() {
        return branchId;
    }

    public int getPayrollId() {
        return payrollId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    public void setPayrollId(int payrollId) {
        this.payrollId = payrollId;
    }
}
