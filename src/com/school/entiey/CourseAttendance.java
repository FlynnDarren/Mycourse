package com.school.entiey;

import com.school.repository.StudentRepository;

public class CourseAttendance {
    private int  course_id;//�γ̺�
    private int  student_id;//ѧ��
    private String type;//ǩ������
    private String date;//�Ͽ�ʱ��
    private String arrdate;//ǩ��ʱ��
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


    public  int allnum;//�Ͽ�Ӧ������
    public int arrnum;//�Ͽ��ѵ�����



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
