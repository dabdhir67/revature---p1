package com.revature.data;

import com.revature.model.Employee;
import com.revature.model.Reimbursement;
import com.revature.service.ConnectionService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {

    private ConnectionService connectionService = new ConnectionService();
//        Employee employee;

    @Override
    public Employee getEmployeeById(int employeeId) {

//        connectionService = new ConnectionService();
        String sql = "select * from employee where employee_id =?";

        try{
            Connection connection = connectionService.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1,employeeId);
            ResultSet resultSet = pstmt.executeQuery();
            Employee employee = new Employee();
            while(resultSet.next()){
                employee.setEmployeeId(resultSet.getInt("employee_id"));
                employee.setFirstName(resultSet.getString("first_name"));
                employee.setLastName(resultSet.getString("last_name"));
                employee.setUserRole(resultSet.getString("user_role"));
                employee.setEmail(resultSet.getString("email"));
                employee.setPassword(resultSet.getString("password"));
            }
            return employee;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
    private ArrayList<Employee> getSubsetOfEmployees(String criteria){
        String sql;
        switch(criteria){
            case "all":
                sql = "select * from employee order by employee_id";
                break;
            default:
                throw new RuntimeException("not supported criteria");
        }
        return getEmployeesBySql(sql);

    }

    private ArrayList<Employee> getEmployeesBySql(String sql){
        ArrayList<Employee> allEmployees = new ArrayList<>();
        try {

            Connection c = connectionService.getConnection();
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                // for each ticket in our result set, we can create an employee object
                int id = rs.getInt("employee_id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String userRole = rs.getString("user_role");
                String email = rs.getString("email");
                Employee e = new Employee();
                e.setEmployeeId(id);
                e.setFirstName(firstName);
                e.setLastName(lastName);
                e.setUserRole(userRole);
                e.setEmail(email);
                allEmployees.add(e);
            }
            System.out.println(allEmployees.get(0));
            System.out.println(allEmployees.get(1));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return allEmployees;
    }

    public ArrayList<Employee> getAll() {
        return getSubsetOfEmployees("all");
    }

    @Override
    public Employee getEmployeeByEmailAndPassword(String email, String password) {
        String sql = "select * from employee where email =? AND pass_word=?";
        Employee employee = new Employee();

        try{
            Connection connection = connectionService.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            ResultSet resultSet = pstmt.executeQuery();

            while(resultSet.next()){
                employee.setEmployeeId(resultSet.getInt("employee_id"));
                employee.setFirstName(resultSet.getString("first_name"));
                employee.setLastName(resultSet.getString("last_name"));
                employee.setUserRole(resultSet.getString("user_role"));
                employee.setEmail(resultSet.getString("email"));
                employee.setPassword(resultSet.getString("pass_word"));
            }
            return employee;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
