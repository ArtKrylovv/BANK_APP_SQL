package com.solvd.bankapp.bin;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.sql.Date;
import java.util.List;

@JsonPropertyOrder({"id", "firstName", "lastName", "role", "addressList", "payroll", "dataHired"})
//@JsonRootName(value ="Employee")
public class Employee {
    @JsonProperty("id")
    private int id;
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("role")
    private String role;
    @JsonProperty("payroll")
    private Payroll payroll;
    // created N:M for JSON practise, no SQL tables/ dao implementation
    @JsonProperty("addresses")
    private List<Address> addressList;
    // created for JSON practise, no SQL tables/ dao implementation
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-mm-dd")
    private Date dateHired;

    public Employee(int id, String firstName, String lastName, String role, List<Address> addressList,Payroll payroll, Date dateHired) {
        // consider refactoring to reduce number of FKs in the table
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.addressList = addressList;
        this.payroll = payroll;
        this.dateHired = dateHired;
    }

    public Employee() {
    }

    public Date getDateHired() {
        return dateHired;
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

    public List<Address> getAddress() {
        return addressList;
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

    public void setAddress(List<Address> addressList) {
        this.addressList = addressList;
    }

    public void setPayroll(Payroll payroll) {
        this.payroll = payroll;
    }

    public void setDateHired(Date dateHired) {
        this.dateHired = dateHired;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role='" + role + '\'' +
                ", address=" + addressList +
                ", payroll=" + payroll +
                ", dateHired=" + dateHired +
                '}';
    }
}
