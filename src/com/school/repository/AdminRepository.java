package com.school.repository;

import com.school.entiey.Admin;

public interface AdminRepository {
    public Admin login(String name , String password);
    public boolean editPassword(Admin admin,String newPassword);
}
