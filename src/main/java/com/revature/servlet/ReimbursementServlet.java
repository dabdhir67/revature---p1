package com.revature.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.Reimbursement;
import com.revature.service.ReimbursementService;
import com.revature.service.ReimbursementServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementServlet extends HttpServlet {

    private ReimbursementService reimbursementService = new ReimbursementServiceImpl();
    private ObjectMapper objectMapper = new ObjectMapper();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        List<Reimbursement> allReimbursements = reimbursementService.getAllReimbursements();
        String json = objectMapper.writeValueAsString(allReimbursements);
        resp.getWriter().write(json);
        resp.setStatus(200);


    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String type = req.getParameter("type");
        System.out.println(type);

        if (type.equals("update")) {
            System.out.println(req.getParameter("id"));
            Integer idParam = Integer.parseInt(req.getParameter("id"));
            int i = reimbursementService.completeReimbursementById(idParam);
            if (i==1){
                System.out.println("Updated.");
            } else {
                System.out.println("Something wrong.");
            }
        } else {
            Reimbursement re = new Reimbursement();
            re.setAmount(Double.parseDouble(req.getParameter("amount")));
            re.setDescription(req.getParameter("description"));
            re.setEmployee(Integer.parseInt(req.getParameter("employee_id")));
            int i = reimbursementService.addReimbursement(re);
            }
        }
    }



//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
//        System.out.println(req.getParameter("id"));
////        Integer idParam = Integer.valueOf(req.getParameter("reimbursement_id"));
////        System.out.println("id : " + idParam);
////        int i = reimbursementService.completeReimbursementById(idParam);
////        if (i==1){
////            System.out.println("Updated.");
////        } else {
////            System.out.println("Something wrong.");
////        }
//////
//////    }
//    }
//}
