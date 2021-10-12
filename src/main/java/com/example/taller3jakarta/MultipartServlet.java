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

        String fileName = "hhh";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        try {
            for (Part part : request.getParts()) {
                if (part.getSubmittedFileName() != null) {
                    fileName = part.getSubmittedFileName();

                }
                part.write(uploadPath + File.separator + fileName);
            }
            request.setAttribute("message", "El archivo se ha subido correctamente!" + fileName+dtf.format(now));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        getServletContext().getRequestDispatcher("/result.jsp").forward(request, response);

    }

    public void destroy() {
    }
}