package com.example.taller3jakarta;
import com.example.taller3jakarta.dtos.Pet;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "jsonServlet", value = "/jsonServlet")
public class JsonServlet extends HttpServlet{

    public void doGet(HttpServletRequest request, HttpServletResponse response)  throws IOException {
        response.setContentType("application/json");

        ArrayList<Pet> pets = new ArrayList<Pet>();

        pets.add(new Pet("Max", "hola.png" ));
        pets.add(new Pet("Firulais", "5D1AD085-6CC1-431C-B80B-ABDB35181CB4-1-192x192.png"));

        PrintWriter out = response.getWriter();
        out.println(new Gson().toJson(pets));
    }
}
