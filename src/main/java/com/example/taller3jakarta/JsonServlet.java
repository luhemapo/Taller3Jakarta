package co.edo.unbosque.jakartaeenew.jakartaeenew;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "jsonServlet", value = "/jsonServlet")
public class JsonServlet extends HttpServlet{

    public void doGet(HttpServletRequest request, HttpServletResponse response)  throws IOException {
        response.setContentType("application/json");

        PrintWriter out = response.getWriter();
            out.println("{'test': 'test'}");
        }
    }

