package com.solvd.bankapp.model;

public class LoanApproval {
    private int id;
    private boolean approvalStatus;
    private int loanApplicationId;
    private Employee employee;

    public LoanApproval(int id, boolean approvalStatus, int loanApplicationId, Employee employee) {
        this.id = id;
        this.approvalStatus = approvalStatus;
        this.loanApplicationId = loanApplicationId;
        this.employee = employee;
    }

    public LoanApproval() {
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

    public Employee getEmployee() {
        return employee;
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

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "LoanApproval{" +
                "id=" + id +
                ", approvalStatus=" + approvalStatus +
                ", loanApplicationId=" + loanApplicationId +
                ", employee=" + employee +
                '}';
    }
}
