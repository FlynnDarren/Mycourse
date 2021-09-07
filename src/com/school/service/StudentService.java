package com.school.service;

import com.school.entiey.Page;
import com.school.entiey.Student;

import java.util.List;

public interface StudentService {
    public boolean deleteStudent(String ids);
    public boolean editStudent(Student student);
    public List<Student> getStudentList(Student student, Page page);
    public int getStudentListTotal(Student student);
    public boolean addStudent(Student student);

}
