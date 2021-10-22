package com.revature.service;

import com.revature.model.Reimbursement;

import java.util.List;

public interface ReimbursementService {

    public List<Reimbursement> getAllReimbursements();
    public int completeReimbursementById(int id);
    public Reimbursement getByEmployeeId(int id);
    public int addReimbursement(Reimbursement reimbursement);
}
