package com.school.service;

import com.school.entiey.Admin;
import com.school.entiey.Student;
import com.school.entiey.Teacher;

public interface SystemService {
    public boolean editPassword(Admin admin, String newPassword);
    public boolean editPassword(Teacher teacher, String newPassword);
    public boolean editPassword(Student teacher, String newPassword);
}
