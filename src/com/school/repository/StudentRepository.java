package com.school.repository;

import com.school.entiey.Page;
import com.school.entiey.Student;

import java.util.List;

public interface StudentRepository {
    public boolean addStudent(Student student);
    public boolean editStudent(Student student);
    public boolean setStudentPhoto(Student student);
    public boolean deleteStudent(String ids);
    public Student getStudent(int id);
    public List<Student> getStudentList(Student student, Page page);
    public int getStudentListTotal(Student student);
    public Student login(String name ,String password);
    public boolean editPassword(Student student,String newPassword);
}
