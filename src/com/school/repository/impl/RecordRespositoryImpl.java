package com.school.repository.impl;

import com.school.ai.Aishell;
import com.school.entiey.Attendance;
import com.school.entiey.Records;
import com.school.util.DbUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecordRespositoryImpl extends BaseRepositotyImpl{
    DbUtil dbUtil = new DbUtil();

    //��Ӽ�¼��Уʱ��
    public void insertRecordsLeaveDate(){
        String re_leave_date = Aishell.getBack_date();//��ý���ѧУʱ��
        String reIdStr = Aishell.num;//ѧ��
        Integer reId = Integer.parseInt(reIdStr);
        String sql = "insert into records (id,leavetime,backtime) values(?,?,?)";
        PreparedStatement preparedStatement  =  null;
        try {
            preparedStatement = dbUtil.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1,reId);
            preparedStatement.setString(2,re_leave_date);
            preparedStatement.setString(3,null);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            dbUtil.closeCon();
        }

    }

    //���¼�¼��Уʱ��
    public void insertRecordsInDate(){
        String re_in_date = Aishell.getBack_date();//��ý���ѧУʱ��
        String reIdStr = Aishell.num;//ѧ��
        Integer reId = Integer.parseInt(reIdStr);
        String  sql  =  "update  records  set  backtime =?   where  id=?";
        PreparedStatement preparedStatement  =  null;
        try {
            preparedStatement = dbUtil.getConnection().prepareStatement(sql);
            preparedStatement.setString(1,re_in_date);
            preparedStatement.setInt(2,reId);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            dbUtil.closeCon();
        }
    }

    //���������ǼǼ�¼
    public List<Records> selectRecordDate(){
        List<Records> ret = new ArrayList<Records>();
        String sql = "select count,records.id,name,leavetime,backtime,type from  records ,s_student where records.id=s_student.id";
        ResultSet resultSet = query(sql);

        try {
            while (resultSet.next()) {
               Records records = new Records();
               records.setCount(resultSet.getInt("count"));
               records.setId(resultSet.getInt("id"));
               records.setName(resultSet.getString("name"));
               records.setLeavetime(resultSet.getString("leavetime"));
               records.setBacktime(resultSet.getString("backtime"));
               records.setType(resultSet.getString("type"));
               ret.add(records);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            dbUtil.closeCon();
        }
        return ret;
    }

    //��id��ѯ
    public List<Records> select_id_Recordsdata( int id){
        String sql = "select count,records.id,name,leavetime,backtime,type from  records ,s_student where records.id=s_student.id and records.id =?";
        PreparedStatement preparedStatement  =  null;
        ResultSet resultSet=null;
        List<Records> ret = new ArrayList<Records>();
        Records records = null;
        try {
            preparedStatement = dbUtil.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1,id);
            resultSet  =  preparedStatement.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
            try {
                while (resultSet.next()) {
                    records.setCount(resultSet.getInt("count"));
                    records.setId(resultSet.getInt("id"));
                    records.setName(resultSet.getString("name"));
                    records.setLeavetime(resultSet.getString("leavetime"));
                    records.setBacktime(resultSet.getString("backtime"));
                    records.setType(resultSet.getString("type"));
                    ret.add(records);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                dbUtil.closeCon();
            }

        return ret;
    }


}
