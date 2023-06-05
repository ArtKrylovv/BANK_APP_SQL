package com.solvd.bankapp.model;

public class LoanApproval {
    private int id;
    private boolean approvalStatus;
    private int loanApplicationId;
    private int employeeId;

    public LoanApproval(boolean approvalStatus, int loanApplicationsId, int employeesId) {
        this.approvalStatus = approvalStatus;
        this.loanApplicationId = loanApplicationsId;
        this.employeeId = employeesId;
    }

    public int getId() {
        return id;
    }

    public boolean isApprovalStatus() {
        return approvalStatus;
    }

    public int getLoanApplicationId() {
        return loanApplicationId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setApprovalStatus(boolean approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public void setLoanApplicationId(int loanApplicationId) {
        this.loanApplicationId = loanApplicationId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
}
