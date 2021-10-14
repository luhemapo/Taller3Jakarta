package com.example.taller3jakarta;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;


import java.io.*;
import java.util.ArrayList;

@WebServlet(name = "csv", value = "/csv")
public class csv extends HttpServlet{

    ArrayList <User> users = new ArrayList<User>();
    private String message;

    public void init() {
        message = "hola";
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        try{
            readCsv();
            for(int i=0;i<users.size();i++) {
                request.setAttribute("rows", "<tr> <th>" + users.get(i).getEmail() + "</th>"+
                                " <th>" + users.get(i).getUserName() + "</th>"+
                                " <th>" + users.get(i).getRole() + "</th>"+
                                " <th>" + users.get(i).getPetName() + "</th>"+
                                " <th>  <button> Ver Imagen </button> </th> </tr>"
                        );
            }
            //request.setAttribute("message", users.get(0).getEmail());
            getServletContext().getRequestDispatcher("/admin.jsp").forward(request, response);

        }catch(IOException e)
        {
            System.out.println("Algo salio mal");
           // e.printStackTrace();
        }
    }

        String[] HEADERS = {"email", "userName", "password", "role", "petName"};

        public void readCsv() throws IOException {

            Reader in = new FileReader("C:\\Users\\luhem\\OneDrive\\Documentos\\Intellij\\Taller3Jakarta\\src\\main\\java\\com\\example\\taller3jakarta\\db.csv");
            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withHeader(HEADERS)
                    .withFirstRecordAsHeader()
                    .parse(in);
            for (CSVRecord record : records) {
                String email = record.get("email");
                String userName = record.get("userName");
                String password = record.get("password");
                String role = record.get("role");
                String petName = record.get("petName");
                User u = new User(email, userName, password, role, petName);
                users.add(u);
            }
            System.out.println("------------------------------------------");
            System.out.println(users.get(0).toString());
            System.out.println(users.size());
        }
    }
