package com.school.service.impl;

import com.school.entiey.Course;
import com.school.entiey.Page;
import com.school.repository.impl.CourseRepositoryImpl;
import com.school.service.CourseService;

import java.util.List;

public class CourserServiceImpl implements CourseService {

    CourseRepositoryImpl courseRepositoryImpl = new CourseRepositoryImpl();
    @Override
    public boolean deleteCourse(String ids) {
       boolean dcour =  courseRepositoryImpl.deleteCourse(ids);
        courseRepositoryImpl.closeCon();
        return dcour;
    }

    @Override
    public boolean editCourse(Course course) {
       boolean edit = courseRepositoryImpl.editCourse(course);
        courseRepositoryImpl.closeCon();
        return edit;
    }

    @Override
    public List<Course> getCourseList(Course course, Page page) {
        List<Course> list =  courseRepositoryImpl.getCourseList(course,page);
        courseRepositoryImpl.closeCon();
        return list;
    }

    @Override
    public int getCourseListTotal(Course course) {
       int tatol = courseRepositoryImpl.getCourseListTotal(course);
       courseRepositoryImpl.closeCon();
        return tatol;
    }

    @Override
    public boolean addCourse(Course course) {
       boolean couser =  courseRepositoryImpl.addCourse(course);
       courseRepositoryImpl.closeCon();
        return couser;
    }
}
