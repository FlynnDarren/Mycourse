package com.school.service;

import com.school.entiey.Page;
import com.school.entiey.Teacher;

import java.util.List;

public interface TeacherService {
    public boolean deleteTeacher(String ids);
    public boolean editTeacher(Teacher teacher);
    public List<Teacher> getTeacherList(Teacher teacher, Page page);
    public int getTeacherListTotal(Teacher teacher);
    public boolean addTeacher(Teacher teacher);

}
