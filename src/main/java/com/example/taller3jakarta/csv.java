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
import java.util.Scanner;

@WebServlet(name = "csv", value = "/csv")
public class csv extends HttpServlet{

    ArrayList <User> users;
    private String message;

    public void init() {
        message = "hola";
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
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
                        " <th>  <img src='uploads/"+users.get(i).getRuta()+"' width='50'> </th> </tr>";


            }
            request.setAttribute("rows", filas);
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

            }
        }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
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


}
