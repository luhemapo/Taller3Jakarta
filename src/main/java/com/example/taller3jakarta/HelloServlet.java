package com.example.taller3jakarta;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");


        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + "</h1>");
        out.println("</body></html>");
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String username = request.getParameter("UserName");
        String password = request.getParameter("PassWord");
        System.out.println("Usuario: "+ username);
        System.out.println("Pass: "+ password);

        Cookie cookie = new Cookie("User",username);
        cookie.setMaxAge(3600);
        response.addCookie(cookie);



        if(username.equals("admin") && password.equals("12345")){
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html>");
            out.println("<meta http-equiv='refresh' content='0; URL=admin.jsp'>");
            out.println("</html>");
        } else if (username.equals("user") && password.equals("12345")){
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html>");
            out.println("<meta http-equiv='refresh' content='0; URL=user.html'>");
            out.println("</html>");
        }else {
            response.sendError(401);
        }
    }

    public void destroy() {
    }
}