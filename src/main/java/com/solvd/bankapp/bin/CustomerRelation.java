package com.solvd.bankapp.bin;

public class CustomerRelation {
    private int id;
    private String notes;
    private int employeeId;
    private int customerId;

    public CustomerRelation(String notes, int employeesId, int customersId) {
        this.notes = notes;
        this.employeeId = employeesId;
        this.customerId = customersId;
    }

    public int getId() {
        return id;
    }

    public String getNotes() {
        return notes;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}
