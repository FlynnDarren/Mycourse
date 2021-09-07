package com.school.controller;

import com.school.ai.Aishell;
import com.school.repository.impl.CourseAttendanceRepositoryImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/courseAttendance")
public class AiCourseAttendanceServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Aishell aishell = new Aishell();
        CourseAttendanceRepositoryImpl courseAttendanceRepository = new CourseAttendanceRepositoryImpl();
        try {
            aishell.FaceRec();
            courseAttendanceRepository.InsertDate();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
