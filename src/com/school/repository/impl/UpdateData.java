package com.school.repository.impl;

import com.school.entiey.CourseAttendance;
import com.school.util.DbUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UpdateData {

    public static void DBUpdate(List<CourseAttendance> list) {


        DbUtil dbUtil = new DbUtil();
        PreparedStatement preparedStatement3 = null;

        PreparedStatement preparedStatement4 = null;
//        String sql3 = "update  s_course_attendance  set  course_id=?,type=?,date=?,count=? where student_id=? and course_id is null";
        String sql3 = "update  s_course_attendance  set  course_id=?,type=?,date=?,count=?";

        String sql4="select * from s_course_attendance where count=0 ";

        try {
            preparedStatement3 = dbUtil.getConnection().prepareStatement(sql3);
            preparedStatement4 = dbUtil.getConnection().prepareStatement(sql4);

            ResultSet query_id = preparedStatement4.executeQuery();

            while (query_id.next()){
                for (int t = 0; t < list.size(); t++) {

                if(query_id.getInt("student_id")==list.get(t).getStudent_id()){
                    preparedStatement3.setInt(1, list.get(t).getCourse_id());
                    preparedStatement3.setString(2, list.get(t).getType());
                    preparedStatement3.setString(3, list.get(t).getDate());
                    preparedStatement3.setInt(4, list.get(t).getArrnum());
                    preparedStatement3.executeUpdate();
                }
                }
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        dbUtil.closeCon();
    }


}





