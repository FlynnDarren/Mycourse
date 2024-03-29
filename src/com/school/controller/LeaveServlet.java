package com.school.controller;


import com.school.ai.Aishell;
import com.school.entiey.Leave;
import com.school.entiey.Page;
import com.school.entiey.Student;
import com.school.entiey.Time;
import com.school.service.LeaveService;
import com.school.service.impl.LeaveServiceImpl;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeaveServlet extends HttpServlet {

	/**
	 * 
	 */
	LeaveService leaveService = new LeaveServiceImpl();
	private static final long serialVersionUID = 1L;
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String method = request.getParameter("method");
		if("toLeaveServletListView".equals(method)){
			try {
				request.getRequestDispatcher("view/leaveList.jsp").forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if("AddLeave".equals(method)){
			addLeave(request,response);
		}else if("LeaveList".equals(method)){
			getLeaveList(request,response);
		}else if("EditLeave".equals(method)){
			editLeave(request,response);
		}else if("CheckLeave".equals(method)){
			checkLeave(request,response);
		}
		else if("DeleteLeave".equals(method)){
			deleteLeave(request,response);
		}
	}
	private void deleteLeave(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		String msg = "success";
		if(!leaveService.deleteLeave(id)){
			msg = "error";
		}
		try {
			response.getWriter().write(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void checkLeave(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int studentId = Integer.parseInt(request.getParameter("studentid"));
		int id = Integer.parseInt(request.getParameter("id"));
		int status = Integer.parseInt(request.getParameter("status"));
		String info = request.getParameter("info");
		String remark = request.getParameter("remark");
		Leave leave = new Leave();
		leave.setStudentId(studentId);
		leave.setInfo(info);
		leave.setRemark(remark);
		leave.setStatus(status);
		leave.setId(id);

		String date = request.getParameter("date");
		String over_date = request.getParameter("over_date");
		leave.setDate(date);
		leave.setOver_date(over_date);

		String msg = "error";
		if(leaveService.editLeave(leave)){
			msg = "success";
		}
		try {
			response.getWriter().write(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	private void editLeave(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		int studentId = Integer.parseInt(request.getParameter("studentid"));
		int id = Integer.parseInt(request.getParameter("id"));
		String info = request.getParameter("info");
		Leave leave = new Leave();
		leave.setStudentId(studentId);
		leave.setInfo(info);
		leave.setRemark("");
		leave.setStatus(Leave.LEAVE_STATUS_WAIT);
		leave.setId(id);

		String date = request.getParameter("date");
		String over_date = request.getParameter("over_date");
		leave.setDate(date);
		leave.setOver_date(over_date);
		String msg = "error";

		if(leaveService.editLeave(leave)){
			msg = "success";
		}
		try {
			response.getWriter().write(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void getLeaveList(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		int studentId = request.getParameter("studentid") == null ? 0 : Integer.parseInt(request.getParameter("studentid").toString());
		Integer currentPage = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
		Integer pageSize = request.getParameter("rows") == null ? 999 : Integer.parseInt(request.getParameter("rows"));
		Leave leave = new Leave();
		//获取当前登录用户类型
		int userType = Integer.parseInt(request.getSession().getAttribute("userType").toString());
		if(userType == 2){
			//如果是学生，只能查看自己的信息
			Student currentUser = (Student)request.getSession().getAttribute("user");
			studentId = currentUser.getId();
		}
		leave.setStudentId(studentId);
		List<Leave> leaveList = leaveService.getLeaveList(leave, new Page(currentPage, pageSize));
		int total =leaveService.getLeaveListTotal(leave);
		response.setCharacterEncoding("UTF-8");
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("total", total);
		ret.put("rows", leaveList);
		try {
			String from = request.getParameter("from");
			if("combox".equals(from)){
				response.getWriter().write(JSONArray.fromObject(leaveList).toString());
			}else{
				response.getWriter().write(JSONObject.fromObject(ret).toString());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void addLeave(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		int studentId = Integer.parseInt(request.getParameter("studentid"));
		String info = request.getParameter("info");
		String date = request.getParameter("date");
		String over_date = request.getParameter("over_date");

		//
		Aishell.setOver_date(request.getParameter("over_date"));
		Aishell.setDate(request.getParameter("date"));


		Time.setOver_date(over_date);
		Time.setMydate(date);

		Leave leave = new Leave();
		leave.setStudentId(studentId);
		leave.setInfo(info);
		leave.setRemark("");
		leave.setDate(date);
		leave.setOver_date(over_date);
		String msg = "error";

		if(leaveService.addLeave(leave)){
			msg = "success";
		}
		try {
			response.getWriter().write(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
