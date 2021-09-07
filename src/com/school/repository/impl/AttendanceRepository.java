package com.school.repository.impl;

import com.school.ai.Aishell;
import com.school.entiey.Attendance;
import com.school.entiey.Page;
import com.school.util.DbUtil;
import com.school.util.StringUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AttendanceRepository extends BaseRepositotyImpl implements com.school.repository.AttendanceRepository {



    public int InsertBack_date(String back_date){

        String overDateStr = Aishell.getOver_date();//获得截止时间
        System.out.println("##############1");
        String[] split = null;
        Date myover_date = null;
        Date myback_date =null;
        String o = null;
        split = overDateStr.split("晚上");
        System.out.println("##############2");
        String replace = split[1].replace("10:00:00", " 22:00:00");
        String overDate =split[0]+replace;//获得可以进行比较时间的字符串
        System.out.println("##############3");
        try {
             myover_date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(overDate);
             myback_date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(back_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("##############4");
        if (myback_date.compareTo(myover_date) > 0) {
            o = "是";
        } else {
            o = "否";
        }
        System.out.println("##############5");


        DbUtil dbUtil = new DbUtil();
        DbUtil dbUtil2 = new DbUtil();
        String date = Aishell.getDate();

        String sql1 = "update s_attendance  set type= '"+o+"' where student_id='"+ Aishell.num+"' and  date='"+date+"'";
        System.out.println("#################sql1");
        String sql_insert = "update s_attendance  set back_date = '"+back_date+"' where student_id='"+ Aishell.num+"' and ";
        System.out.println("########################sql_insert");
        sql_insert+="  date="+"'"+date+"'";
        int i = 0;
        try {
            i = dbUtil.getConnection().prepareStatement(sql_insert).executeUpdate();
            System.out.println("##################dubu1");
                dbUtil2.getConnection().prepareStatement(sql1).executeUpdate();
            System.out.println("######################dbu2");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            dbUtil.closeCon();
            dbUtil2.closeCon();
        }
        return i;

    }

    /**
     * 添加考勤信息
     * @param attendance
     * @return
     */
    public boolean addAttendance(Attendance attendance){
//        String sql = "insert into s_attendance values(null,"+attendance.getCourseId()+","+attendance.getStudentId()+",'"+attendance.getType()+"','"+attendance.getDate()+"','"+attendance.getOver_date()+"','"+Aishell.getBack_date()+"')";

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        String format = new SimpleDateFormat("HH:mm:ss").format(date);
        Date date2 = null;
        try {
            date2 = new SimpleDateFormat("HH:mm:ss").parse("22:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        DbUtil dbUtil = new DbUtil();

        Date  myover_date=null;


        try {
            //当前时间
            Date date1 =new  SimpleDateFormat("HH:mm:ss").parse(format);
            if(date1.compareTo(date2)>=0){
                String sql_query="select * from s_attendance where back_date is null";
                ResultSet resultSet = dbUtil.getConnection().prepareStatement(sql_query).executeQuery();


                    String over_date=null;
                    String o = null;
                    String my_date=null;

                    Date parse=null;
                while (resultSet.next()){
                    over_date= resultSet.getString("over_date");


                    String[] split = over_date.split("晚上");
                    System.out.println("////////////");
                    String replace = split[1].replace("10:00:00", " 22:00:00");
                    String overDate =split[0]+replace;//获得可以进行比较时间的字符串
                    System.out.println("////////////");
                    try {
                        myover_date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(overDate);
                        my_date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
                        parse= new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(my_date);


                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    System.out.println("////////////");
                    if (parse.compareTo(myover_date) > 0) {
                        o = "是";
                    } else {
                        o = "否";
                    }


                    String sql1 = "update s_attendance  set type= '"+o+"' where  over_date=  '"+over_date+"'";
                    System.out.println("////////////");

                    int i = dbUtil.getConnection().prepareStatement(sql1).executeUpdate();
                }




            }

        } catch (ParseException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {

            dbUtil.closeCon();
        }



        Attendance.setIs_backAction(true);
        return true;
    }

    /**
     * 判断当前是否已签到
     * @param studentId
     * @param courseId
     * @param type
     * @return
     */
    public boolean isAttendanced(int studentId,int courseId,String type,String date){



        boolean ret = false;
        String sql = "select * from s_attendance where student_id = " + studentId + " and course_id = " + courseId + " and type = '" + type + "' and date = '" + date + "'";




        ResultSet query = query(sql);
        try {
            if(query.next()){
                return true;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * 获取指定的考勤信息列表
     * @param attendace
     * @param page
     * @return
     */
    public List<Attendance> getSelectedCourseList(Attendance attendace, Page page){

        List<Attendance> ret = new ArrayList<Attendance>();
        String sql = "select * from s_attendance ";
        if(attendace.getStudentId() != 0){
            sql += " and student_id = " + attendace.getStudentId();
        }
//        if(attendace.getCourseId() != 0){
//            sql += " and course_id = " + attendace.getCourseId();
//        }
        if(!StringUtil.isEmpty(attendace.getType())){
            String type = attendace.getType();
            sql += " and type = '" + type + "'";
//            sql += " and type = '" + attendace.getType() + "'";
        }
//        if(!StringUtil.isEmpty(attendace.getDate())){
//            sql += " and date = '" + attendace.getDate() + "'";
//        }
//        if(!StringUtil.isEmpty(attendace.getOver_date())){
//            sql += " and over_date = '" + attendace.getOver_date()+ "'";
//        }
//        if(!StringUtil.isEmpty(attendace.getBack_date())){
//            sql += " and date = '" + attendace.getBack_date() + "'";
//        }


        if((Attendance.getIs_backAction()!=null)&&Attendance.getIs_backAction()==true){

            sql+=" where back_date is null";
        }

        sql += " limit " + page.getStart() + "," + page.getPageSize();
        sql = sql.replaceFirst("and", "where");

        ResultSet resultSet = query(sql);
        try {
            while(resultSet.next()){
                Attendance a = new Attendance();
                a.setId(resultSet.getInt("id"));
                a.setCourseId(resultSet.getInt("course_id"));
                a.setStudentId(resultSet.getInt("student_id"));
                a.setType(resultSet.getString("type"));
                a.setDate(resultSet.getString("date"));
                a.setOver_date(resultSet.getString("over_date"));
                a.setBack_date(resultSet.getString("back_date"));
                ret.add(a);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Attendance.setIs_backAction(false);

        return ret;
    }

    /**
     * 获取符合条件记录总数
     * @param attendance
     * @return
     */
    public int getAttendanceListTotal(Attendance attendance){
        int total = 0;
        String sql = "select count(*)as total from s_attendance ";
        if(attendance.getStudentId() != 0){
            sql += " and student_id = " + attendance.getStudentId();
        }
        if(attendance.getCourseId() != 0){
            sql += " and course_id = " + attendance.getCourseId();
        }
        ResultSet resultSet = query(sql.replaceFirst("and", "where"));
        try {
            while(resultSet.next()){
                total = resultSet.getInt("total");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return total;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteAttendance(int id){
        String sql = "delete from s_attendance where id = " + id;
        return update(sql);
    }
}
