package com.school.repository.impl;

import com.school.ai.Aishell;
import com.school.entiey.Leave;
import com.school.entiey.Page;
import com.school.repository.LeaveRepository;
import com.school.util.DbUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class LeaveRepositoryImpl extends BaseRepositotyImpl implements LeaveRepository {
    /**
     * 添加请假信息
     * @param leave
     * @return
     */
    String o =null;
    public boolean addLeave(Leave leave){

        String back_str = Aishell.getBack_date();
        String over_str = Aishell.getOver_date();

        String[] split = null;

        split = over_str.split("晚上");
        String replace = split[1].replace("10:00:00", " 22:00:00");
        String over_str2 =split[0]+replace;
        Date back_date = null;
        Date over_date = null;

        if(back_date == null){
            o="否";
        }else {
            try {

                back_date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(back_str);
                over_date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(over_str2);
                if (back_date.compareTo(over_date) > 0) {
                    o = "是";
                } else {
                    o = "否";
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

//        Date date = new Date();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
//        try {
//            Date parse = simpleDateFormat.parse(back_date);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        String sql = "insert into s_leave values(null,"+leave.getStudentId()+",'"+leave.getInfo()+"',"+Leave.LEAVE_STATUS_WAIT+",'"+leave.getRemark()+"','"+leave.getDate()+"','"+leave.getOver_date()+"')";
//        String sql2="insert into s_attendance values(null,1,"+leave.getStudentId()+",'"+o+"','"+leave.getDate()+"','"+leave.getOver_date()+"',null)";
//        DbUtil dbUtil = new DbUtil();
//        try {
//            int t=dbUtil.getConnection().prepareStatement(sql2).executeUpdate();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
        return update(sql);

    }

    /**
     * 编辑请假单
     * @param leave
     * @return
     */
    public boolean editLeave(Leave leave){
        String sql = "update s_leave set student_id = "+leave.getStudentId()+", info = '"+leave.getInfo()+"',status = "+leave.getStatus()+",remark = '"+leave.getRemark()+"' where id = " + leave.getId();
        if(leave.getStatus()>0){
            String sql2="insert into s_attendance values(null,1,"+leave.getStudentId()+",'"+o+"','"+leave.getDate()+"','"+leave.getOver_date()+"',null)";
            DbUtil dbUtil = new DbUtil();
            try {
                int t=dbUtil.getConnection().prepareStatement(sql2).executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return update(sql);
    }

    /**
     * 删除请假信息
     * @param id
     * @return
     */
    public boolean deleteLeave(int id) {
        // TODO Auto-generated method stub
        String sql = "delete from s_leave where id = " + id ;
        return update(sql);
    }

    /**
     * 获取分页请假单信息列表
     * @param leave
     * @param page
     * @return
     */
    public List<Leave> getLeaveList(Leave leave, Page page){
        List<Leave> ret = new ArrayList<Leave>();
        String sql = "select * from s_leave ";
        if(leave.getStudentId() != 0){
            sql += " and student_id = " + leave.getStudentId() + "";
        }
        sql += " limit " + page.getStart() + "," + page.getPageSize();
        ResultSet resultSet = query(sql.replaceFirst("and", "where"));
        try {
            while(resultSet.next()){
                Leave l = new Leave();
                l.setId(resultSet.getInt("id"));
                l.setStudentId(resultSet.getInt("student_id"));
                l.setInfo(resultSet.getString("info"));
                l.setStatus(resultSet.getInt("status"));
                l.setRemark(resultSet.getString("remark"));
                l.setOver_date(resultSet.getString("over_date"));
                l.setDate(resultSet.getString("date"));
                ret.add(l);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * 获取总记录数
     * @param leave
     * @return
     */
    public int getLeaveListTotal(Leave leave){
        int total = 0;
        String sql = "select count(*)as total from s_leave ";
        if(leave.getStudentId() != 0){
            sql += " and student_id = " + leave.getStudentId() + "";
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
}
