package com.solvd.bankapp.model;

public class Employee {
    private int id;
    private String firstName;
    private String lastName;
    private String role;
    private Address address;
    private Department department;
    private Branch branch;
    private Payroll payroll;

    public Employee(int id, String firstName, String lastName, String role, Address address, Department department, Branch branch, Payroll payroll) {
        // consider refactoring to reduce number of FKs in the table
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.address = address;
        this.department = department;
        this.branch = branch;
        this.payroll = payroll;
    }

    public Employee() {
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

    public Address getAddress() {
        return address;
    }

    public Department getDepartment() {
        return department;
    }

    public Branch getBranch() {
        return branch;
    }

    public Payroll getPayroll() {
        return payroll;
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

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public void setPayroll(Payroll payroll) {
        this.payroll = payroll;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role='" + role + '\'' +
                ", address=" + address +
                ", department=" + department +
                ", branch=" + branch +
                ", payroll=" + payroll +
                '}';
    }
}
