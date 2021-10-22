package com.revature.model;

import java.util.Objects;

public class Reimbursement {

    private int reimbursement_id;
    private Integer employee;
    private double amount;
    private String description;
    private boolean isCompleted;

    public int getReimbursement_id() {
        return reimbursement_id;
    }

    public void setReimbursement_id(int reimbursement_id) {
        this.reimbursement_id = reimbursement_id;
    }

    public Integer getEmployee() {
        return employee;
    }

    public void setEmployee(Integer employee) {
        this.employee = employee;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reimbursement that = (Reimbursement) o;
        return reimbursement_id == that.reimbursement_id && Double.compare(that.amount, amount) == 0 && isCompleted == that.isCompleted && Objects.equals(employee, that.employee) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reimbursement_id, employee, amount, description, isCompleted);
    }

    @Override
    public String toString() {
        return "Reimbursement{" +
                "reimbursement_id=" + reimbursement_id +
                ", employee=" + employee +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", isCompleted=" + isCompleted +
                '}';
    }

    public Reimbursement(int reimbursement_id, Integer employee, double amount, String description, boolean isCompleted) {
        super();
        this.reimbursement_id = reimbursement_id;
        this.employee = employee;
        this.amount = amount;
        this.description = description;
        this.isCompleted = isCompleted;
    }

    public Reimbursement(){
        super();
    }
}
