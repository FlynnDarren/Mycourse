package com.school.repository;

import java.sql.Connection;
import java.sql.ResultSet;

public interface BaseRepositoty {
    public void closeCon();
    public ResultSet query(String sql);
    public boolean update(String sql);
    public Connection getConnection();
}
