package com.revature.service;

import com.revature.model.Employee;
import com.revature.model.EmployeeRole;

public class AuthService {
    private EmployeeService employeeService = new EmployeeServiceImpl();

    public boolean validateToken(String authToken){
        if(authToken==null){
            return false;
        }

        String[] tokenArr = authToken.split(":");
        if(tokenArr.length != 2){ // "a:b:c" "bananas"
            // we first check to see if our token has 2 values, separated by a colon
            // if it has more or less than 2, we return false
            return false;
        }

        // then we take a look at the first value, making sure it's numeric
        String idString = tokenArr[0];
        if(!idString.matches("^\\d+$")){ // if it does not match a number regular expression, we return false
            return false;
        }

        // then we look at the second value, making sure it matches a value in our enum
        String roleString = tokenArr[1];
        EmployeeRole[] roles = EmployeeRole.values(); // GENERAL, ADMIN
        for(EmployeeRole role: roles){
            if(role.toString().equals(roleString)){ // check to see if the second value is one of our enum values
                return true;
            }
        }
        return false;
    }

    public Employee findEmployeeByToken(String authToken){ // "2:admin"
        String[] tokenArr = authToken.split(":");
        int id = Integer.parseInt(tokenArr[0]);
        return employeeService.getEmployeeById(id);
    }

}


