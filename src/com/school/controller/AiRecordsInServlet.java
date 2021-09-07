package com.school.controller;

import com.school.ai.Aishell;
import com.school.repository.impl.RecordRespositoryImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/aiin")
public class AiRecordsInServlet extends HttpServlet {
    RecordRespositoryImpl recordRespository = new RecordRespositoryImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String method = req.getParameter("method");
        if("addRecords".equals(method)){
            addRecords(req,resp);
        }else if("recordsList".equals(method)){

        }else if("deleteRecords".equals(method)){

        }else if("insertRecords".equals(method)){

        }



        Aishell sell = new Aishell();
        try {
            sell.FaceRec();
            recordRespository.insertRecordsInDate();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }


    public void addRecords(HttpServletRequest req, HttpServletResponse resp){

    }




}
