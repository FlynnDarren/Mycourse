package com.school.controller;


import com.school.entiey.Admin;
import com.school.entiey.Student;
import com.school.entiey.Teacher;
import com.school.service.LoginService;
import com.school.service.impl.LoginServiceImpl;
import com.school.util.StringUtil;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 
 * @author wz
 *µÇÂ¼ÑéÖ¤servlet
 */
public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	LoginService loginService = new LoginServiceImpl();
	private static final long serialVersionUID = -5870852067427524781L;
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException{
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String method = request.getParameter("method");
		if("logout".equals(method)){
			logout(request, response);
			return;
		}
		String name = request.getParameter("account");
		String password = request.getParameter("password");
		int type = Integer.parseInt(request.getParameter("type"));

		String loginStatus = "loginFaild";
		switch (type) {
			case 1:{
				Admin admin = loginService.loginAdmin(name, password);
				if(admin == null){
					response.getWriter().write("loginError");
					return;
				}
				HttpSession session = request.getSession();
				session.setAttribute("user", admin);
				session.setAttribute("userType", type);
				loginStatus = "1";
				break;
			}
			case 2:{
				Student student = loginService.loginStudent(name, password);
				if(student == null){
					response.getWriter().write("loginError");
					return;
				}
				HttpSession session = request.getSession();
				session.setAttribute("user", student);
				session.setAttribute("userType", type);
				loginStatus = "2";
				break;
			}
			case 3:{
				Teacher teacher = loginService.loginTeacher(name, password);
				if(teacher == null){
					response.getWriter().write("loginError");
					return;
				}
				HttpSession session = request.getSession();
				session.setAttribute("user", teacher);
				session.setAttribute("userType", type);
				loginStatus = "3";
				break;
			}
			default:
				break;
			}
		response.getWriter().write(loginStatus);
		
	}
	
	private void logout(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.getSession().removeAttribute("user");
		request.getSession().removeAttribute("userType");
		response.sendRedirect("index.jsp");
	}
}
