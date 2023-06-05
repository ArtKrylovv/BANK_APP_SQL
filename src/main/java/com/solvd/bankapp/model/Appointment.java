package com.solvd.bankapp.model;

public class Appointment {
    private int id;
    private String reason;
    private boolean completed;
    private int employeesId;
    private int customersId;

    public Appointment(String reason, boolean completed, int employeesId, int customersId) {
        this.reason = reason;
        this.completed = completed;
        this.employeesId = employeesId;
        this.customersId = customersId;
    }

    public int getId() {
        return id;
    }

    public String getReason() {
        return reason;
    }

    public boolean isCompleted() {
        return completed;
    }

    public int getEmployeesId() {
        return employeesId;
    }

    public int getCustomersId() {
        return customersId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void setEmployeesId(int employeesId) {
        this.employeesId = employeesId;
    }

    public void setCustomersId(int customersId) {
        this.customersId = customersId;
    }

}
