package com.school.repository.impl;

import com.school.entiey.CourseAttendance;
import com.school.util.DbUtil;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class InsertDataRespository {

    public static void InsertData(List<CourseAttendance> list) {
        DbUtil dbUtil = new DbUtil();
        PreparedStatement preparedStatement = null;
        String sql = "insert into c_attendance_history(course_id,student_id,type,date,arrdate,count) values(?,?,?,?,?,?)";
        try {
            preparedStatement = dbUtil.getConnection().prepareStatement(sql);
            for (int i = 0; i <list.size() ; i++) {
                preparedStatement.setInt(1, list.get(i).getCourse_id());
                preparedStatement.setInt(2,list.get(i).getStudent_id());
                preparedStatement.setString(3,list.get(i).getType());
                preparedStatement.setString(4,list.get(i).getDate());
                preparedStatement.setString(5,list.get(i).getArrdate());
                preparedStatement.setInt(6,list.get(i).getArrnum());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            dbUtil.closeCon();
        }
    }
}
