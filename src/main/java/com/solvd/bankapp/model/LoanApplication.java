package com.solvd.bankapp.model;

public class LoanApplication {
    private int id;
    private String object;
    private long amount;
    private double interest;
    private int duration;
    private String dateCreated;
    private Employee employee;
    private Customer customer;
    private LoanApproval loanApproval;

    public LoanApplication(int id, String object, long amount, double interest, int duration,
                           String dateCreated, Employee employee, Customer customer, LoanApproval loanApproval) {
        this.id = id;
        this.object = object;
        this.amount = amount;
        this.interest = interest;
        this.duration = duration;
        this.dateCreated = dateCreated;
        this.employee = employee;
        this.customer = customer;
        this.loanApproval =loanApproval;
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

    public String getDateCreated() {
        return dateCreated;
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

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "LoanApplication{" +
                "id=" + id +
                ", object='" + object + '\'' +
                ", amount=" + amount +
                ", interest=" + interest +
                ", duration=" + duration +
                ", dateCreated='" + dateCreated + '\'' +
                ", employee=" + employee +
                ", customer=" + customer +
                ", loanApproval=" + loanApproval +
                '}';
    }

    public LoanApproval getLoanApproval() {
        return loanApproval;
    }

    public void setLoanApproval(LoanApproval loanApproval) {
        this.loanApproval = loanApproval;
    }
}
