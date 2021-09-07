package com.school.repository;

import com.school.entiey.Leave;
import com.school.entiey.Page;

import java.util.List;

public interface LeaveRepository {
    public boolean addLeave(Leave leave);
    public boolean editLeave(Leave leave);
    public boolean deleteLeave(int id);
    public List<Leave> getLeaveList(Leave leave, Page page);
    public int getLeaveListTotal(Leave leave);
}
