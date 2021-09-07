package com.school.repository.impl;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import com.school.ai.Aishell;
import com.school.entiey.CourseAttendance;
import com.school.util.AddDate;
import com.school.util.CompareDate;
import com.school.util.DbUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CourseAttendanceRepositoryImpl extends BaseRepositotyImpl {
    PreparedStatement preparedStatement = null;
    DbUtil dbUtil = new DbUtil();


    public void InsertDate() {
        String arrdate = Aishell.getBack_date();//识别时间
        String studentStr = Aishell.num;//学号
        Integer studentid = Integer.parseInt(studentStr);


//某一个id 一小时内不能连续插入；
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        String select_sql = "select * from s_course_attendance where student_id=?";
//        try {
//            ps = dbUtil.getConnection().prepareStatement(select_sql);
//            ps.setInt(1, studentid);
//            rs = ps.executeQuery();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        try {
//
//            while (rs.next()) {
//
//
//                String arr_date = rs.getString("arrdate");
//
//                if (arr_date == null) {
//                    break;
//                }
//
//                String[] split = arr_date.split(" ");
//                String addArrDate1 = AddDate.addDateMinut(split[1], 1);
//
//
//                if ("已签到".equals(CompareDate.CompareCoursrDate(split[1], addArrDate1))) {
//                    return;
//                }
//
//
//            }
//
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        CompareDate.CompareCoursrDate(arrdate, AddDate.addDateMinut(arrdate, 1));

        String sql = "insert into s_course_attendance(student_id,type,arrdate) value(?,'已签到',?)";

        try {
            preparedStatement = dbUtil.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, studentid);
            preparedStatement.setString(2, arrdate);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            dbUtil.closeCon();
        }


    }

    public List<CourseAttendance> selectCourseAttendance() {
        List<CourseAttendance> courseAttendanceArrayList = new ArrayList<>();
        ResultSet resultSet = null;
        String sql = "select *from  s_course_attendance ";


        try {
            preparedStatement = dbUtil.getConnection().prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                CourseAttendance all = new CourseAttendance();
                all.setDate(resultSet.getString("date"));
                all.setCourse_id(resultSet.getInt("course_id"));

                all.setType(resultSet.getString("type"));
                all.setArrdate(resultSet.getString("arrdate"));
                all.setStudent_id(resultSet.getInt("student_id"));
                courseAttendanceArrayList.add(all);//将所有选课（学生的id,课程id,开始时间,总人数）写入上课考勤list
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return courseAttendanceArrayList;
//        return null;

    }

    //查询学生考勤情况
    public List<CourseAttendance> selectCourseAttendance(int course_id) {
        List<CourseAttendance> courseAttendanceArrayList = new ArrayList<>();
        ResultSet resultSet = null;
        String sql = "select course_date,selected_num from s_course where id=?";//拿到上课开始时间，选课人数(应到人数)


        try {
            preparedStatement = dbUtil.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, course_id);
            resultSet = preparedStatement.executeQuery();

            String date = null;
            Integer selectNum = null;
            while ((resultSet.next())) {
                date = resultSet.getString("course_date");
                selectNum = resultSet.getInt("selected_num");//将开课时间，人数存入实体对象
            }


            //通过课程id 拿到学生的id
            String sql1 = "select student_id from s_selected_course where course_id=?";
            preparedStatement = dbUtil.getConnection().prepareStatement(sql1);
            preparedStatement.setInt(1, course_id);
            resultSet = preparedStatement.executeQuery();//拿到所有选课的学生id
            while (resultSet.next()) {
                CourseAttendance all = new CourseAttendance();
                all.setStudent_id(resultSet.getInt("student_id"));
                all.setCourse_id(course_id);
//                assert date != null;
//                assert selectNum != null;
                all.setDate(date);
                all.setAllnum(selectNum);
                courseAttendanceArrayList.add(all);//将所有选课（学生的id,课程id,开始时间,总人数）写入上课考勤list
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            dbUtil.closeCon();
        }

        //通过课程id 拿到课程name
        String sql0 = "select name from s_course where id=?";
        PreparedStatement preparedStatement0 = null;
        try {
            preparedStatement0 = dbUtil.getConnection().prepareStatement(sql0);
            preparedStatement0.setInt(1, course_id);
            resultSet = preparedStatement0.executeQuery();
            while (resultSet.next()) {
                for (int i = 0; i < courseAttendanceArrayList.size(); i++) {
                    courseAttendanceArrayList.get(i).setCourseName(resultSet.getString("name"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbUtil.closeCon();
        }


/////////////////name
        String sql6 = "select  *from s_student ";
        PreparedStatement preparedStatement6 = null;
        try {
            preparedStatement6 = dbUtil.getConnection().prepareStatement(sql6);
            resultSet = preparedStatement6.executeQuery();//拿到所有选课的学生id
            while (resultSet.next()) {
                for (int i = 0; i < courseAttendanceArrayList.size(); i++) {
                    if (courseAttendanceArrayList.get(i).getStudent_id() == resultSet.getInt("id"))
                        courseAttendanceArrayList.get(i).setStudentName(resultSet.getString("name"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbUtil.closeCon();
        }


        //查询已经参加考勤学生的id arrdate(后面可能需要+条件)
        String sql1 = "select * from s_course_attendance where course_id =?";
        ResultSet resultSet1 = null;
        PreparedStatement preparedStatement1 = null;
        //将已经签到学生的arrdate写入 list
        try {
            preparedStatement1 = dbUtil.getConnection().prepareStatement(sql1);
            preparedStatement1.setInt(1, course_id);
            resultSet1 = preparedStatement1.executeQuery();
            while (resultSet1.next()) {
                CourseAttendance attendance = new CourseAttendance();
                attendance.setArrdate(resultSet1.getString("arrdate"));
                for (int i = 0; i < courseAttendanceArrayList.size(); i++) {
                    if (courseAttendanceArrayList.get(i).getStudent_id() == resultSet1.getInt("student_id")) {
                        courseAttendanceArrayList.get(i).setArrdate(resultSet1.getString("arrdate"));
                    }
                }

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        String type = null;
        try {
//            while (resultSet1.next()) {
//                    for(int i = 0; i<courseAttendanceArrayList.size();i++){
//                       if(resultSet1.getInt("student_id") == courseAttendanceArrayList.get(i).getStudent_id()){
//                           courseAttendanceArrayList.get(i).setArrdate(resultSet1.getString("arrdate"));
//                           //如果同一人，同一节课，重复考勤，拿最新的arrdate;
//                           //同一天，不同课，拿最新的arrdate都可以解决（update 空课程id）
//                       }
//                    }
//            }
            //update list签到情况
            int sum = 0;
            for (int i = 0; i < courseAttendanceArrayList.size(); i++) {
                if (courseAttendanceArrayList.get(i).getArrdate() == null) {
                    courseAttendanceArrayList.get(i).setType("旷课");
                    sum++;
                    continue;
                }

                type = CompareDate.CompareCoursrDate(courseAttendanceArrayList.get(i).getArrdate(), courseAttendanceArrayList.get(i).getDate());
                courseAttendanceArrayList.get(i).setType(type);

            }


            int count = courseAttendanceArrayList.get(0).getAllnum() - sum;
            for (int i = 0; i < courseAttendanceArrayList.size(); i++) {
                courseAttendanceArrayList.get(i).setArrnum(count);//已经到人数;
            }


//            PreparedStatement preparedStatement3 = null;
//            String sql3 = "update  s_course_attendance  set  course_id=?,type=?,date=?,count=? where student_id=? and course_id is null";
//            preparedStatement3 = dbUtil.getConnection().prepareStatement(sql3);
//
//            for (int i = 0; i < courseAttendanceArrayList.size(); i++) {
//
//
//
//
//                System.out.println("////////////////" + courseAttendanceArrayList.get(i).getType()+"////////////////");
//                //此处将id 所有的记录都更新了；应该只更新已经有课程id的；
//
//                preparedStatement3.setInt(1, courseAttendanceArrayList.get(i).getCourse_id());
//                preparedStatement3.setString(2, courseAttendanceArrayList.get(i).getType());
//                System.out.println("===========================");
//                preparedStatement3.setString(3, courseAttendanceArrayList.get(i).getDate());
//                preparedStatement3.setInt(4, count);
//                preparedStatement3.setInt(5, courseAttendanceArrayList.get(i).getStudent_id());
//                preparedStatement3.executeUpdate();
//            }
//
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        } finally {
//            dbUtil.closeCon();
//        }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return courseAttendanceArrayList;
    }
}

