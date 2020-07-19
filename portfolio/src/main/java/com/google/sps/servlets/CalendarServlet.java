package com.google.sps.servlets;
import com.google.gson.Gson;
import com.google.sps.data.Comment;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.json.gson.GsonFactory;
import java.io.File;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import java.util.Arrays;
import com.google.api.services.calendar.Calendar;

@WebServlet("/cal")
public class CalendarServlet extends HttpServlet {

   @Override 
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;");
        response.getWriter().println("CAL IS WORKING");    
    }
}
