package com.school.controller;

import com.school.entiey.CourseAttendance;
import com.school.repository.impl.CourseAttendanceRepositoryImpl;
import com.school.repository.impl.InsertDataRespository;
import com.school.repository.impl.UpdateData;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/CourseAttendanceServlet")
public class CourseAttendanceServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        String method = req.getParameter("method");
        List<CourseAttendance> list=null;
        if("toCourseServletListView".equals(method)) {
            try {
                req.getRequestDispatcher("view/courseAttendanceList.jsp").forward(req, resp);
            } catch (ServletException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }else if("CourseAttendance".equals(method)) {
            CourseAttendanceRepositoryImpl courseAttendanceRepository = new CourseAttendanceRepositoryImpl();
            String coures_idStr = null;
            Integer coures_id = null;
//            List<CourseAttendance> courseAttendances = courseAttendanceRepository.selectCourseAttendance();
//            if (req.getParameter("course_id") == null) {
//                resp.getWriter().write(JSONArray.fromObject(courseAttendances).toString());
//                System.out.println(JSONArray.fromObject(courseAttendanceRepository.selectCourseAttendance()));
//
//
//            } else {
            coures_idStr = req.getParameter("course_id");
            coures_id = Integer.parseInt(coures_idStr);
            list = courseAttendanceRepository.selectCourseAttendance(coures_id);
                UpdateData.DBUpdate(list);
            resp.getWriter().write(JSONArray.fromObject(list).toString());
//            req.getRequestDispatcher("view/courseAttendanceList.jsp").forward(req,resp);//去修改的页面
            System.out.println(JSONArray.fromObject(courseAttendanceRepository.selectCourseAttendance(coures_id)));
//            }
        }else if("courseAttendanceHistory".equals(method)){
            CourseAttendanceRepositoryImpl courseAttendanceRepository = new CourseAttendanceRepositoryImpl();
            String coures_idStr = null;
            Integer coures_id = null;
            coures_idStr = req.getParameter("course_id");
            coures_id = Integer.parseInt(coures_idStr);
            list = courseAttendanceRepository.selectCourseAttendance(coures_id);
            InsertDataRespository.InsertData(list);
//            req.getRequestDispatcher("view/courseAttendanceList.jsp").forward(req,resp);//去修改的页面
            System.out.println(JSONArray.fromObject(courseAttendanceRepository.selectCourseAttendance(coures_id)));
//            }


        }

    }
}
