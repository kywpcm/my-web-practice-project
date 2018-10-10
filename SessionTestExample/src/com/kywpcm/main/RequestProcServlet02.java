package com.kywpcm.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@javax.servlet.annotation.WebServlet(name = "RequestProcServlet02")
public class RequestProcServlet02 extends javax.servlet.http.HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        System.out.println("RequestProcServlet02.doPost");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        System.out.println("RequestProcServlet02.doGet");

        HttpSession session = request.getSession();
        String id = (String) session.getAttribute("id");
        String name = (String) session.getAttribute("name");
        Integer age = (Integer) session.getAttribute("age");

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head>");
        out.println("<title>main page 02</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<p> id : " + id + "</p>");
        out.println("<p> name : " + name + "</p>");
        out.println("<p> age : " + age + "</p>");
        out.println("</body>");
        out.println("</html>");
    }

}
