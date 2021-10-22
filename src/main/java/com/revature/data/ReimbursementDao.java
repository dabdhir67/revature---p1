package com.revature.data;

import com.revature.model.Reimbursement;

import java.util.ArrayList;
import java.util.List;

public interface ReimbursementDao {

    public ArrayList<Reimbursement> getAll();
    public Reimbursement getByEmployeeId(int id);
    public int completeReimbursementById(int id);
    public int addReimbursement(Reimbursement reimbursement);
    public ArrayList<Reimbursement> getPendingReimbursements();
}
