package com.school.service.impl;

import com.school.entiey.Page;
import com.school.entiey.Student;
import com.school.repository.impl.StudentRepositoryImpl;
import com.school.service.StudentService;

import java.util.List;

public class StudentServiceImpl implements StudentService {
    StudentRepositoryImpl studentRepositoryImpl = new StudentRepositoryImpl();
    @Override
    public boolean deleteStudent(String ids) {
      boolean stu =  studentRepositoryImpl.deleteStudent(ids);
        studentRepositoryImpl.closeCon();
        return stu;
    }

    @Override
    public boolean editStudent(Student student) {
       boolean stu =  studentRepositoryImpl.editStudent(student);
        studentRepositoryImpl.closeCon();
        return stu;
    }

    @Override
    public List<Student> getStudentList(Student student, Page page) {
        List<Student> clazzList = studentRepositoryImpl.getStudentList(student,page);
        studentRepositoryImpl.closeCon();
        return clazzList;
    }

    @Override
    public int getStudentListTotal(Student student) {
        int total = studentRepositoryImpl.getStudentListTotal(student);
        studentRepositoryImpl.closeCon();
        return total;
    }

    @Override
    public boolean addStudent(Student student) {
       boolean stu =  studentRepositoryImpl.addStudent(student);
        studentRepositoryImpl.closeCon();
        return stu;
    }
}
