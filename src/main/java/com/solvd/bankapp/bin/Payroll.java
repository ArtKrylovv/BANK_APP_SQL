package com.solvd.bankapp.bin;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Payroll {
    private int id;
    // replace with enum
    @JsonProperty("payType")
    private PayType payType;
    @JsonProperty("annualSalary")
    private int annualSalary;
    @JsonProperty("hourlyRate")
    private int hourlyRate;

    public Payroll() {
    }

    public int getId() {
        return id;
    }

    public PayType getPayType() {
        return payType;
    }

    public int getAnnualSalary() {
        return annualSalary;
    }

    public int getHourlyRate() {
        return hourlyRate;
    }

    public Payroll(int id, PayType payType, int annualSalary, int hourlyRate) {
        this.id = id;
        this.payType = payType;
        this.annualSalary = annualSalary;
        this.hourlyRate = hourlyRate;


    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPayType(PayType payType) {
        this.payType = payType;
    }

    public void setAnnualSalary(int annualSalary) {
        this.annualSalary = annualSalary;
    }

    public void setHourlyRate(int hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    @Override
    public String toString() {
        return "Payroll{" +
                "id=" + id +
                ", payType=" + payType +
                ", annualSalary=" + annualSalary +
                ", hourlyRate=" + hourlyRate +
                '}';
    }
}

