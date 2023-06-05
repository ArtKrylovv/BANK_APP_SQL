package com.solvd.bankapp.model;

public class Payroll {
    private int id;
    private String payType;
    private int annualSalary;
    private int hourlyRate;

    public Payroll(String payType, int annualSalary, int hourlyRate) {
        this.payType = payType;
        this.annualSalary = annualSalary;
        this.hourlyRate = hourlyRate;
    }

    public int getId() {
        return id;
    }

    public String getPayType() {
        return payType;
    }

    public Integer getAnnualSalary() {
        return annualSalary;
    }

    public Integer getHourlyRate() {
        return hourlyRate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public void setAnnualSalary(Integer annualSalary) {
        this.annualSalary = annualSalary;
    }

    public void setHourlyRate(Integer hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public void setAnnualSalary(int annualSalary) {
        this.annualSalary = annualSalary;
    }

    public void setHourlyRate(int hourlyRate) {
        this.hourlyRate = hourlyRate;
    }
}
