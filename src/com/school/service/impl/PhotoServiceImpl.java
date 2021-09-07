package com.school.service.impl;

import com.school.entiey.Student;
import com.school.entiey.Teacher;
import com.school.repository.impl.StudentRepositoryImpl;
import com.school.repository.impl.TeacherRepositoryImpl;
import com.school.service.PhotoService;

public class PhotoServiceImpl implements PhotoService {
    @Override
    public boolean setStudentPhoto(Student student) {
        StudentRepositoryImpl studentRepositoryImpl = new StudentRepositoryImpl();
       boolean stu = studentRepositoryImpl.setStudentPhoto(student);
        studentRepositoryImpl.closeCon();
        return stu;
    }

    @Override
    public boolean setTeacherPhoto(Teacher teacher) {
        TeacherRepositoryImpl teacherRepositoryImpl = new TeacherRepositoryImpl();
       boolean tea = teacherRepositoryImpl.setTeacherPhoto(teacher);
       teacherRepositoryImpl.closeCon();
        return tea;
    }

    @Override
    public Student getStudent(int id) {
        StudentRepositoryImpl studentRepositoryImpl = new StudentRepositoryImpl();
        Student student = studentRepositoryImpl.getStudent(id);
        studentRepositoryImpl.closeCon();
        return student;
    }

    @Override
    public Teacher getTeacher(int id) {
        TeacherRepositoryImpl teacherRepositoryImpl = new TeacherRepositoryImpl();
        Teacher teacher = teacherRepositoryImpl.getTeacher(id);
        teacherRepositoryImpl.closeCon();
        return teacher;
    }

    @Override
    public boolean editPassword(Student student, String newPassword) {
        return false;
    }
}
