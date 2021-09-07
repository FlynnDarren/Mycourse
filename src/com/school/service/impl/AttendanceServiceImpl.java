package com.school.service.impl;

import com.school.entiey.Attendance;
import com.school.entiey.Course;
import com.school.entiey.Page;
import com.school.entiey.SelectedCourse;
import com.school.repository.impl.AttendanceRepository;
import com.school.repository.impl.CourseRepositoryImpl;
import com.school.repository.impl.SelectedCourseRepositoryImpl;
import com.school.service.AttendanceService;
import com.school.util.DateFormatUtil;

import java.util.Date;
import java.util.List;

public class AttendanceServiceImpl implements AttendanceService {
    @Override
    public String deleteAttendance(int id) {
        AttendanceRepository attendanceRepository = new AttendanceRepository();
        String msg = "success";
        if(!attendanceRepository.deleteAttendance(id)){
            msg = "error";
        }
        attendanceRepository.closeCon();
        return msg;
    }

    @Override
    public List<Attendance> getSelectedCourseList(Attendance attendance, Page page) {
        AttendanceRepository attendanceRepository = new AttendanceRepository();
        List<Attendance> attendanceList = attendanceRepository.getSelectedCourseList(attendance, page);
        attendanceRepository.closeCon();
        return attendanceList;
    }

    @Override
    public int getAttendanceListTotal(Attendance attendance) {
        AttendanceRepository attendanceRepository = new AttendanceRepository();
        int total = attendanceRepository.getAttendanceListTotal(attendance);
        attendanceRepository.closeCon();
        return total;
    }

    @Override
    public boolean isAttendanced(int studentId, int courseId, String type, String date) {
        AttendanceRepository attendanceRepository = new AttendanceRepository();
       boolean isatt = attendanceRepository.isAttendanced(studentId, courseId, type, DateFormatUtil.getFormatDate(new Date(), "yyyy-MM-dd"));
        attendanceRepository.closeCon();
            return isatt;
    }

    @Override
    public boolean addAttendance(Attendance attendance) {
        AttendanceRepository attendanceRepository = new AttendanceRepository();
       boolean att = attendanceRepository.addAttendance(attendance);
        attendanceRepository.closeCon();
        return att;
    }

    @Override
    public List<SelectedCourse> getSelectedCourseList(SelectedCourse selectedCourse, Page page) {
        SelectedCourseRepositoryImpl selectedCourseRepositoryImpl = new SelectedCourseRepositoryImpl();
        List<SelectedCourse> selectedCourseList = selectedCourseRepositoryImpl.getSelectedCourseList(selectedCourse, new Page(1, 999));
        selectedCourseRepositoryImpl.closeCon();
        return selectedCourseList;
    }

    @Override
    public List<Course> getCourse(String courseId) {
        CourseRepositoryImpl courseRepositoryImpl = new CourseRepositoryImpl();
        List<Course> courseList = courseRepositoryImpl.getCourse(courseId);
        courseRepositoryImpl.closeCon();
        return courseList;
    }
}
