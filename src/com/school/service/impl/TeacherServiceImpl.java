package com.school.service.impl;

import com.school.entiey.Page;
import com.school.entiey.Teacher;
import com.school.repository.impl.TeacherRepositoryImpl;
import com.school.service.TeacherService;

import java.util.List;

public class TeacherServiceImpl implements TeacherService {
    TeacherRepositoryImpl teacherRepositoryImpl = new TeacherRepositoryImpl();
    @Override
    public boolean deleteTeacher(String ids) {
      boolean tea =  teacherRepositoryImpl.deleteTeacher(ids);
        teacherRepositoryImpl.closeCon();
        return tea;
    }

    @Override
    public boolean editTeacher(Teacher teacher) {
     boolean tea =  teacherRepositoryImpl.editTeacher(teacher);
        teacherRepositoryImpl.closeCon();
        return tea;
    }

    @Override
    public List<Teacher> getTeacherList(Teacher teacher, Page page) {
        List<Teacher> teacherList = teacherRepositoryImpl.getTeacherList(teacher, page);
        teacherRepositoryImpl.closeCon();
        return teacherList;
    }

    @Override
    public int getTeacherListTotal(Teacher teacher) {
        int total = teacherRepositoryImpl.getTeacherListTotal(teacher);
        teacherRepositoryImpl.closeCon();
        return total;
    }

    @Override
    public boolean addTeacher(Teacher teacher) {
      boolean tea = teacherRepositoryImpl.addTeacher(teacher);
        teacherRepositoryImpl.closeCon();
        return tea;
    }
}
