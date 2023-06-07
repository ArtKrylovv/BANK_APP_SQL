package com.solvd.bankapp.bin;

public class Appointment {
    private int id;
    private String reason;
    private boolean completed;
    private Employee employee;
    private Customer customer;

    public Appointment(int id, String reason, boolean completed, Employee employee, Customer customer) {
        this.id = id;
        this.reason = reason;
        this.completed = completed;
        this.employee = employee;
        this.customer = customer;
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

    public Employee getEmployee() {
        return employee;
    }

    public Customer getCustomer() {
        return customer;
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

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", reason='" + reason + '\'' +
                ", completed=" + completed +
                ", employee=" + employee +
                ", customer=" + customer +
                '}';
    }
}
