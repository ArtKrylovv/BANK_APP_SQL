package com.solvd.bankapp.model;

import java.time.LocalDateTime;

public class LoanApplication {
    private int id;
    private String object;
    private long amount;
    private double interest;
    private int duration;
    private LocalDateTime created;
    private int employeeId;
    private int customerId;

    public LoanApplication(String object, long amount, double interest, int duration, LocalDateTime created, int employeesId, int customerId) {
        this.object = object;
        this.amount = amount;
        this.interest = interest;
        this.duration = duration;
        this.created = created;
        this.employeeId = employeesId;
        this.customerId = customerId;
    }

    public int getId() {
        return id;
    }

    public String getObject() {
        return object;
    }

    public long getAmount() {
        return amount;
    }

    public double getInterest() {
        return interest;
    }

    public int getDuration() {
        return duration;
    }

    public LocalDateTime getCreated() {
        return created;
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

    public void setObject(String object) {
        this.object = object;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}
