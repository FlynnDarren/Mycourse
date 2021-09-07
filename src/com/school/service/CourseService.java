package com.school.service;

import com.school.entiey.Course;
import com.school.entiey.Page;

import java.util.List;

public interface CourseService {
    public boolean deleteCourse(String ids);
    public boolean editCourse(Course course);
    public List<Course> getCourseList(Course course, Page page);
    public int getCourseListTotal(Course course);
    public boolean addCourse(Course course);

}
