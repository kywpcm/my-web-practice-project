package com.kywpcm.main;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "RequestProcServlet01", urlPatterns = "/RequestProcServlet01")
public class RequestProcServlet01 extends javax.servlet.http.HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        System.out.println("RequestProcServlet01.doPost");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        System.out.println("RequestProcServlet01.doGet");

        HttpSession session = request.getSession();
        String id = (String) session.getAttribute("id");
        String name = (String) session.getAttribute("name");
        Integer age = (Integer) session.getAttribute("age");

        System.out.println("id : " + id);
        System.out.println("name : " + name);
        System.out.println("age : " + age);
    }

}
