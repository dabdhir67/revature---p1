package com.revature.servlet;

import com.revature.model.Employee;
import com.revature.service.EmployeeService;
import com.revature.service.EmployeeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthServlet extends HttpServlet {

    private EmployeeService employeeService = new EmployeeServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // get username and password from request
        // because we're using .getParameter with a post request, our servlet is expecting
        // key value pairs in the request body, along with the application/x-www-formurlencoded content type
        String emailParam = req.getParameter("email");
        String passwordParam = req.getParameter("pass_word");
//        String idStringParam = req.getParameter("id"); // params not present will be null
        System.out.println("Credentials received: "+emailParam +" "+passwordParam);


        // check to see if user/pass match a user in the db
        Employee employee = employeeService.getByCredentials(emailParam, passwordParam);

        // sent 401 (Unauthorized) if we can't find a user with those credentials
        try {
            if (employee == null) {
                resp.sendError(401, "User credentials provided did not return a valid account");
            } else {
                // send 200 (OK) if we do find a user with those credentials
                resp.setStatus(200);
                // we can also send back some token that identifies the particular user that matched
                String token = employee.getEmployeeId() + ":" + employee.getUserRole();
                resp.setHeader("Authorization", token);
            }
        } catch(Exception e){
            resp.setStatus(418);
        }
    }
}
