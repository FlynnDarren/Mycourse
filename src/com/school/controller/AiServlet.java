package com.school.controller;


import com.school.ai.Aishell;
import com.school.repository.impl.AttendanceRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ai")
public class AiServlet extends HttpServlet {

    private AttendanceRepository attendanceRepository =new AttendanceRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Aishell sell = new Aishell();
        try {
            sell.FaceRec();
        int i = attendanceRepository.InsertBack_date(Aishell.getBack_date());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
