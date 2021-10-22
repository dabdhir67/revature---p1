package com.revature.service;

import com.revature.data.EmployeeDao;
import com.revature.data.EmployeeDaoImpl;
import com.revature.data.ReimbursementDao;
import com.revature.data.ReimbursementDaoImpl;
import com.revature.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDao employeeDao = new EmployeeDaoImpl();

    public ArrayList<Employee> getAllEmployees() {
        return (ArrayList<Employee>) employeeDao.getAll();
    }

    public Employee getByCredentials(String email, String password) {
        return employeeDao.getEmployeeByEmailAndPassword(email, password);
    }

    public Employee getEmployeeById(int id) {
        Employee employee = employeeDao.getEmployeeById(id);
        return employeeDao.getEmployeeById(id);
    }
}
