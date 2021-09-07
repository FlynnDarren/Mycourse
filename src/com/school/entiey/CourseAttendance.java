package com.school.entiey;

import com.school.repository.StudentRepository;

public class CourseAttendance {
    private int  course_id;//课程号
    private int  student_id;//学号
    private String type;//签到类型
    private String date;//上课时间
    private String arrdate;//签到时间
    private String courseName;
    private String studentName;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }


    public  int allnum;//上课应到人数
    public int arrnum;//上课已到人数



    public CourseAttendance() {
    }

    public CourseAttendance(int course_id, int student_id, String type, String date, String arrdate) {
        this.course_id = course_id;
        this.student_id = student_id;
        this.type = type;
        this.date = date;
        this.arrdate = arrdate;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
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

    public String getArrdate() {
        return arrdate;
    }

    public void setArrdate(String arrdate) {
        this.arrdate = arrdate;
    }


    public int getAllnum() {
        return allnum;
    }

    public void setAllnum(int allnum) {
        this.allnum = allnum;
    }

    public int getArrnum() {
        return arrnum;
    }

    public void setArrnum(int arrnum) {
        this.arrnum = arrnum;
    }
}
