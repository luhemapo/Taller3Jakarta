package com.example.taller3jakarta;
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "multiPartServlet", value = "/multiPartServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 5 * 5)

public class MultipartServlet extends HttpServlet {
    private String message;
    private String UPLOAD_DIRECTORY = "uploads";

    public void init() {
        message = "hola";
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdir();


        String fileName = "";
        String petName = request.getParameter("name");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy/MM/dd_HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String date = dtf.format(now);
        String date2 = dtf2.format(now);
        String ruta= uploadPath + File.separator + date + "_" + fileName;
        try {
            for (Part part : request.getParts()) {
                if (part.getSubmittedFileName() != null) {
                    fileName = part.getSubmittedFileName();
                }
                part.write(uploadPath + File.separator + date + "_" + fileName);

            }
            //JOptionPane.showMessageDialog(null, "La imagen " + "*" + fileName + "*" + " de tu mascota " + petName + " ha sido guardada satisfactoriamente!");
            //request.setAttribute("message", "La imagen " + "*" + fileName + "*" + " de tu mascota " + petName + " ha sido guardada satisfactoriamente!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        getServletContext().getRequestDispatcher("/user.html").forward(request, response);
        String username = request.getParameter("name");
        Cookie[] cookies = request.getCookies();

        escribirArchivo(uploadPath + File.separator, username, date2, fileName, ruta);
    }

    public void destroy() {
    }

    public void escribirArchivo( String s, String uname, String date2, String filename, String ruta){
        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter(s+"prueba.txt",true);
            pw = new PrintWriter(fichero);
            pw.println(uname+","+date2+","+filename+","+ruta);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fichero)
                    fichero.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}