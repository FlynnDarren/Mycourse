package com.school.service;

import com.school.entiey.Student;
import com.school.entiey.Teacher;

public interface PhotoService {
    public boolean setStudentPhoto(Student student);
    public boolean setTeacherPhoto(Teacher teacher);
    public Student getStudent(int id);
    public Teacher getTeacher(int id);
    public boolean editPassword(Student student,String newPassword);

}
