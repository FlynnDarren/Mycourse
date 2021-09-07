package com.school.repository;

import com.school.entiey.Page;
import com.school.entiey.SelectedCourse;

import java.util.List;

public interface SelectedCourseRepository {
    public List<SelectedCourse> getSelectedCourseList(SelectedCourse selectedCourse, Page page);
    public int getSelectedCourseListTotal(SelectedCourse selectedCourse);
    public boolean isSelected(int studentId,int courseId);
    public boolean addSelectedCourse(SelectedCourse selectedCourse);
    public boolean deleteSelectedCourse(int id);
    public SelectedCourse getSelectedCourse(int id);
    int SearchCourseCount(int stuid);
}
