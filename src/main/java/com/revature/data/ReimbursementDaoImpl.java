package com.revature.data;

import com.revature.model.Reimbursement;
import com.revature.service.ConnectionService;



import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementDaoImpl implements ReimbursementDao{

    private ConnectionService connectionService = new ConnectionService();

    private ArrayList<Reimbursement> getSubsetOfReimbursements(String criteria){
        String sql;
        switch(criteria){
            case "all":
                sql = "select * from reimbursement order by reimbursement_id";
                break;
            case "pending":
                sql = "select * from reimbursement where is_completed = false order by reimbursement_id";
                break;
            case "completed":
                sql = "select * from task where is_completed = true order by task_id order by reimbursement_id";
                break;
            default:
                throw new RuntimeException("not supported criteria");
        }
        return getReimbursementsBySql(sql);

    }

    private ArrayList<Reimbursement> getReimbursementsBySql(String sql){
        ArrayList<Reimbursement> allReimbursements = new ArrayList<>();
        try {

            Connection c = connectionService.getConnection();
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                // for each ticket in our result set, we can create a reimbursement object
                int id = rs.getInt("reimbursement_id");
                int e_id = rs.getInt("employee_id");
                Double amount = rs.getDouble("amount");
                String description = rs.getString("description");
                boolean isCompleted = rs.getBoolean("is_completed");
                Reimbursement r = new Reimbursement();
                r.setReimbursement_id(id);
                r.setEmployee(e_id);
                r.setAmount(amount);
                r.setDescription(description);
                r.setCompleted(isCompleted);
                allReimbursements.add(r);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return allReimbursements;
    }

    public ArrayList<Reimbursement> getPendingReimbursements(){
        return getSubsetOfReimbursements("pending");
    }


    public ArrayList<Reimbursement> getAll(){
        return getSubsetOfReimbursements("all");
    }


//    public List<Reimbursement> getAll() {
//        ArrayList<Reimbursement> reimbursements = new ArrayList<>();
//
//        try{
//            Connection c = connectionService.getConnection();
//            String sql = "select * from reimbursement";
//            PreparedStatement pstmt = c.prepareStatement(sql);
//            ResultSet resultSet = pstmt.executeQuery();
//            Reimbursement r = new Reimbursement();
//
//            while(resultSet.next()){
//                r.setReimbursement_id(resultSet.getInt("reimbursement_id"));
//                r.setAmount(resultSet.getDouble("amount"));
//                r.setDescription(resultSet.getString("description"));
//                r.setEmployee(resultSet.getInt("employee_id"));
//                r.setCompleted(resultSet.getBoolean("is_completed"));
//                reimbursements.add(r);
//            }
//            System.out.println(reimbursements.get(0));
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        return reimbursements;
//    }

    @Override
    public Reimbursement getByEmployeeId(int id) {
        Reimbursement reimbursement = new Reimbursement();
        String sql = "select * from reimbursement where employee_id = ?";

        try{
            Connection connection = connectionService.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet resultSet = pstmt.executeQuery();
            while(resultSet.next()){
                reimbursement.setReimbursement_id(resultSet.getInt("reimbursement_id"));
                reimbursement.setAmount(resultSet.getDouble("amount"));
                reimbursement.setDescription(resultSet.getString("description"));
                reimbursement.setEmployee(resultSet.getInt("employee_id"));
                reimbursement.setCompleted(resultSet.getBoolean("is_completed"));
            }
            return reimbursement;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public int completeReimbursementById(int id) {
        String sql = "update reimbursement set is_completed = true where reimbursement_id=?";
        try{
            Connection connection = connectionService.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1,id);
            return pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    @Override
    public int addReimbursement(Reimbursement reimbursement) {
        String sql = "insert into reimbursement(amount, description, employee_id, is_completed) values (?, ?, ?, false)";
        try {
            Connection connection = connectionService.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setDouble(1, reimbursement.getAmount());
            pstmt.setString(2, reimbursement.getDescription());
            pstmt.setInt(3, reimbursement.getEmployee());
            return pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }
}
