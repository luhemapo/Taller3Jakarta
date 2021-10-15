package com.example.taller3jakarta;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    ArrayList <User> users;
    private String message;

    public void init() {
        message = "hola";
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

            try{
                //readCsv();
                users = new ArrayList<User>();
                leerArchivo();
                String filas="";
                for(int i=0;i<users.size();i++) {
                    filas = filas+"<tr> <th>" + users.get(i).getDate() + "</th>"+
                            " <th>" + users.get(i).getEmail() + "</th>"+
                            " <th>" + users.get(i).getPet() + "</th>"+
                            " <th>" + users.get(i).getPic() + "</th>"+
                            " <th>  <img src='uploads/"+users.get(i).getRuta()+"' width='100'> </th> </tr>";


                }
                request.setAttribute("rows", filas);
                //request.setAttribute("message", users.get(0).getEmail());
                getServletContext().getRequestDispatcher("/admin.jsp").forward(request, response);

            }catch(IOException | ServletException e)
            {
                System.out.println("Algo salio mal");
                // e.printStackTrace();
            }


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

    public void leerArchivo() {
        try {
            String ruta= getServletContext().getRealPath("") + File.separator + "uploads"+File.separator+"prueba.txt";
            Scanner input = new Scanner(new File(ruta));
            while (input.hasNextLine()) {
                String line = input.nextLine();
                String partes[]=line.split(",");
                String pet = partes[0];
                String dte = partes[1];
                String pic = partes [2];
                String rutaPic=partes[3];
                System.out.println(line);
                User u = new User(dte,"user@user.com", pet, pic, rutaPic);
                users.add(u);
            }
            input.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void destroy() {
    }
}