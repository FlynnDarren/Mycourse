package com.school.controller;


import com.school.entiey.*;
import com.school.service.AttendanceService;
import com.school.service.impl.AttendanceServiceImpl;
import com.school.util.DateFormatUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AttendanceServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String method = request.getParameter("method");
		if("toAttendanceServletListView".equals(method)){
			try {
				request.getRequestDispatcher("view/attendanceList.jsp").forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if("AddAttendance".equals(method)){
			AddAttendance(request,response);
		}else if("AttendanceList".equals(method)){
			attendanceList(request,response);
		}else if("DeleteAttendance".equals(method)){
			deleteAttendance(request,response);
		}else if("getStudentSelectedCourseList".equals(method)){
			getStudentSelectedCourseList(request, response);
		}
	}

	private void deleteAttendance(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		AttendanceServiceImpl attendanceService = new AttendanceServiceImpl();
		String msg =  attendanceService.deleteAttendance(id);
		response.getWriter().write(msg);
	}
	private void attendanceList(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int studentId = request.getParameter("studentid") == null ? 0 : Integer.parseInt(request.getParameter("studentid").toString());
		int courseId = request.getParameter("courseid") == null ? 0 : Integer.parseInt(request.getParameter("courseid").toString());
		String type = request.getParameter("type");
		String date = request.getParameter("date");

		Integer currentPage = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
		Integer pageSize = request.getParameter("rows") == null ? 999 : Integer.parseInt(request.getParameter("rows"));
		Attendance attendance = new Attendance();
		//获取当前登录用户类型
		int userType = Integer.parseInt(request.getSession().getAttribute("userType").toString());
		if(userType == 2){
			//如果是学生，只能查看自己的信息
			Student currentUser = (Student)request.getSession().getAttribute("user");
			studentId = currentUser.getId();
		}
		response.setCharacterEncoding("UTF-8");
		attendance.setCourseId(courseId);
		attendance.setStudentId(studentId);
		attendance.setDate(date);
		attendance.setType(type);

		AttendanceService attendanceService = new AttendanceServiceImpl();
		List<Attendance> attendanceList = attendanceService.getSelectedCourseList(attendance, new Page(currentPage, pageSize));
		int total=  attendanceService.getAttendanceListTotal(attendance);
		response.setCharacterEncoding("UTF-8");
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("total", total);
		ret.put("rows", attendanceList);
		try {
			String from = request.getParameter("from");
			if("combox".equals(from)){
				response.getWriter().write(JSONArray.fromObject(attendanceList).toString());
			}else{
				response.getWriter().write(JSONObject.fromObject(ret).toString());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void AddAttendance(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int studentId = request.getParameter("studentid") == null ? 0 : Integer.parseInt(request.getParameter("studentid").toString());
		int courseId = request.getParameter("courseid") == null ? 0 : Integer.parseInt(request.getParameter("courseid").toString());
//		String type = request.getParameter("type").toString();
		String type = "sss";
		Attendance attendance = new Attendance();
		attendance.setCourseId(courseId);
		attendance.setStudentId(studentId);
		attendance.setType(type);
		attendance.setDate(DateFormatUtil.getFormatDate(new Date(), "yyyy/MM/dd HH:mm:ss"));
		String over_date = Time.getOver_date();
		String date = Time.getMydate();

		attendance.setOver_date(over_date);
		attendance.setDate(date);

		String msg = "success";
		response.setCharacterEncoding("UTF-8");
		AttendanceService attendanceService = new AttendanceServiceImpl();
		boolean isatt= attendanceService.isAttendanced(studentId, courseId, type,DateFormatUtil.getFormatDate(new Date(), "yyyy-MM-dd"));
		boolean att = attendanceService.addAttendance(attendance);
		if(isatt){
			msg = "已签到，请勿重复签到！";
		}else if(!att){
			msg = "系统内部出错。请联系管理员！";
		}
		try {
			response.getWriter().write(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void getStudentSelectedCourseList(HttpServletRequest request, HttpServletResponse response) {



		// TODO Auto-generated method stub
		int studentId = request.getParameter("student_id") == null ? 0 : Integer.parseInt(request.getParameter("student_id").toString());
		SelectedCourse selectedCourse = new SelectedCourse();
		selectedCourse.setStudentId(studentId);

		AttendanceService attendanceService = new AttendanceServiceImpl();
		List<SelectedCourse> selectedCourseList = attendanceService.getSelectedCourseList(selectedCourse, new Page(1, 999));
		String courseId = "";
		for(SelectedCourse sc : selectedCourseList){
			courseId += sc.getCourseId()+ ",";
		}
		courseId = courseId.substring(0,courseId.length()-1);
		List<Course> courseList = attendanceService.getCourse(courseId);
		response.setCharacterEncoding("UTF-8");
		try {
			response.getWriter().write(JSONArray.fromObject(courseList).toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
