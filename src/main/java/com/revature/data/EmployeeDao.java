package com.revature.data;

import com.revature.model.Employee;

import java.util.List;

public interface EmployeeDao {
    public Employee getEmployeeById(int employeeId);
    public List<Employee> getAll();
    public Employee getEmployeeByEmailAndPassword(String username, String password);

}
