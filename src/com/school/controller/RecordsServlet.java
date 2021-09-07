package com.school.controller;

import com.school.entiey.Records;
import com.school.repository.impl.RecordRespositoryImpl;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/selectrecords")
public class RecordsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RecordRespositoryImpl recordRespository = new RecordRespositoryImpl();
        resp.setCharacterEncoding("UTF-8");
        List<Records> ret = new ArrayList<>();
        String method = req.getParameter("method");
            if(method==null)
                method = "selectall";

     switch (method){
         case "selectall":
          ret =  recordRespository.selectRecordDate();
             resp.getWriter().write(JSONArray.fromObject(ret).toString());
             break;
         case "selectid":
             int studentId = Integer.parseInt(req.getParameter("studentid"));
             ret = recordRespository.select_id_Recordsdata(studentId);
             resp.getWriter().write(JSONArray.fromObject(ret).toString());
             break;
     }

    }
}
