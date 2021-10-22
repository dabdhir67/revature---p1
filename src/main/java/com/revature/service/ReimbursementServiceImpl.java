package com.revature.service;

import com.revature.data.ReimbursementDao;
import com.revature.data.ReimbursementDaoImpl;
import com.revature.model.Reimbursement;

import java.util.List;

public class ReimbursementServiceImpl implements ReimbursementService {

    private ReimbursementDao reimbursementDao = new ReimbursementDaoImpl();

    public List<Reimbursement> getAllReimbursements() {
        List<Reimbursement> reimbursements = reimbursementDao.getAll();
//        System.out.println(reimbursements.get(0));
        return reimbursements;
    }


    public int completeReimbursementById(int id) {
        return reimbursementDao.completeReimbursementById(id);
    }

    public Reimbursement getByEmployeeId(int id) {
        return reimbursementDao.getByEmployeeId(id);
    }


    public int addReimbursement(Reimbursement reimbursement) {
        return reimbursementDao.addReimbursement(reimbursement);
    }
}
