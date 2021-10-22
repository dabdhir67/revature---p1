package com.revature.service;

import com.revature.model.Employee;

import java.util.List;

public interface EmployeeService {

    public List<Employee> getAllEmployees();
    public Employee getByCredentials(String email, String password);
    public Employee getEmployeeById(int id);
}
