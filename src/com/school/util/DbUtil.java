package com.school.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 *���ݿ���util
 */
public class DbUtil {

	private String dbUrl = "jdbc:mysql://localhost:3306/db_student_manager_web?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
	private String dbUser = "root";
	private String dbPassword = "root";

	private String jdbcName = "com.mysql.cj.jdbc.Driver";
	private static Connection connection = null;
	public Connection getConnection(){
		try {
			Class.forName(jdbcName);
			connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	
	public void closeCon(){
		if(connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		com.school.util.DbUtil dbUtil = new com.school.util.DbUtil();
		dbUtil.getConnection();
	}

}
