package com.school.repository;

import com.school.entiey.Page;
import com.school.entiey.Teacher;

import java.util.List;

public interface TeacherRepository {
    public boolean addTeacher(Teacher teacher);
    public boolean editTeacher(Teacher teacher);
    public boolean setTeacherPhoto(Teacher teacher);
    public boolean deleteTeacher(String ids);
    public Teacher getTeacher(int id);
    public List<Teacher> getTeacherList(Teacher teacher, Page page);
    public int getTeacherListTotal(Teacher teacher);
    public Teacher login(String name ,String password);
    public boolean editPassword(Teacher teacher,String newPassword);

}
