package com.school.entiey;
/**
 * ¿¼ÇÚ±í
 */
public class Attendance {
	private int id;
	private int courseId;
	private int studentId;
	private String type;
	private String date;
	private String over_date;
	private String back_date;
	private static Boolean Is_backAction;

	public static Boolean getIs_backAction() {
		return Is_backAction;
	}

	public static void setIs_backAction(Boolean is_backAction) {
		Is_backAction = is_backAction;
	}

	public String getBack_date() {
		return back_date;
	}

	public void setBack_date(String back_date) {
		this.back_date = back_date;
	}

	public String getOver_date() {
		return over_date;
	}

	public void setOver_date(String over_date) {
		this.over_date = over_date;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCourseId() {
		return 1;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
}
