package com.school.service.impl;

import com.school.entiey.Admin;
import com.school.entiey.Student;
import com.school.entiey.Teacher;
import com.school.repository.impl.AdminRepositoryImpl;
import com.school.repository.impl.StudentRepositoryImpl;
import com.school.repository.impl.TeacherRepositoryImpl;
import com.school.service.SystemService;

public class SystemServiceImpl  implements SystemService {
    @Override
    public boolean editPassword(Admin admin, String newPassword) {
        AdminRepositoryImpl adminRepositoryImpl = new AdminRepositoryImpl();
        boolean adm =  adminRepositoryImpl.editPassword(admin, newPassword);
        adminRepositoryImpl.closeCon();
        return adm;
    }

    @Override
    public boolean editPassword(Teacher teacher, String newPassword) {
        TeacherRepositoryImpl teacherRepositoryImpl = new TeacherRepositoryImpl();
      boolean tea =   teacherRepositoryImpl.editPassword(teacher, newPassword);
        teacherRepositoryImpl.closeCon();
        return tea;
    }

    @Override
    public boolean editPassword(Student student, String newPassword) {
        StudentRepositoryImpl studentRepository = new StudentRepositoryImpl();
        boolean stu =  studentRepository.editPassword(student,newPassword);
        studentRepository.closeCon();
        return stu;
    }
}
