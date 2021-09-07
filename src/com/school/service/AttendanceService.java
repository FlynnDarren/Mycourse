package com.school.service;

import com.school.entiey.Attendance;
import com.school.entiey.Course;
import com.school.entiey.Page;
import com.school.entiey.SelectedCourse;

import java.util.List;

public interface AttendanceService {
    public String deleteAttendance(int id);
    public List<Attendance> getSelectedCourseList(Attendance attendance, Page page);
    public int getAttendanceListTotal(Attendance attendance);
    public boolean isAttendanced(int studentId,int courseId,String type,String date);
    public boolean addAttendance(Attendance attendance);
    public List<SelectedCourse> getSelectedCourseList(SelectedCourse selectedCourse, Page page);
    public List<Course> getCourse(String ids);
}
