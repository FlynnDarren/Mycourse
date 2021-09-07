package com.school.service;

import com.school.entiey.Admin;
import com.school.entiey.Student;
import com.school.entiey.Teacher;

public interface LoginService {
    public Admin loginAdmin(String name , String password);
    public Student loginStudent(String name , String password);
    public Teacher loginTeacher(String name , String password);


}
