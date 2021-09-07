package com.school.repository;

import com.school.entiey.Course;
import com.school.entiey.Page;

import java.util.List;

public interface CourseRepository {
    public boolean addCourse(Course course);
    public List<Course> getCourseList(Course course, Page page);
    public int getCourseListTotal(Course course);
    public boolean editCourse(Course course);
    public boolean deleteCourse(String ids);
    public boolean isFull(int courseId);
    public void updateCourseSelectedNum(int courseId ,int num);
    public List<Course> getCourse(String ids);
}
