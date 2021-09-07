package com.school.repository;

import com.school.entiey.Attendance;
import com.school.entiey.Page;

import java.util.List;

public interface AttendanceRepository {
    public int InsertBack_date(String back_date);
    public boolean addAttendance(Attendance attendance);
    public boolean isAttendanced(int studentId,int courseId,String type,String date);
    public List<Attendance> getSelectedCourseList(Attendance attendace, Page page);
    public int getAttendanceListTotal(Attendance attendance);
    public boolean deleteAttendance(int id);
}
