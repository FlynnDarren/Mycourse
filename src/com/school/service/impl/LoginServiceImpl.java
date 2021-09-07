package com.school.service.impl;

import com.school.entiey.Admin;
import com.school.entiey.Student;
import com.school.entiey.Teacher;
import com.school.repository.impl.AdminRepositoryImpl;
import com.school.repository.impl.StudentRepositoryImpl;
import com.school.repository.impl.TeacherRepositoryImpl;
import com.school.service.LoginService;

public class LoginServiceImpl implements LoginService {


    @Override
    public Admin loginAdmin(String name, String password) {
        AdminRepositoryImpl adminRepositoryImpl = new AdminRepositoryImpl();
        Admin admin = adminRepositoryImpl.login(name, password);
        adminRepositoryImpl.closeCon();
        return admin;
    }

    @Override
    public Student loginStudent(String name, String password) {
        StudentRepositoryImpl studentRepositoryImpl = new StudentRepositoryImpl();
        Student student = studentRepositoryImpl.login(name, password);
        studentRepositoryImpl.closeCon();
        return student;
    }

    @Override
    public Teacher loginTeacher(String name, String password) {
        TeacherRepositoryImpl teahcerRepositoryImpl = new TeacherRepositoryImpl();
        Teacher teacher = teahcerRepositoryImpl.login(name, password);
        teahcerRepositoryImpl.closeCon();
        return teacher;
    }
}
