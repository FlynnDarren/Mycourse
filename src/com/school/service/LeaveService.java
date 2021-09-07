package com.school.service;

import com.school.entiey.Leave;
import com.school.entiey.Page;

import java.util.List;

public interface LeaveService {
    public boolean deleteLeave(int id);
    public boolean editLeave(Leave leave);
    public List<Leave> getLeaveList(Leave leave, Page page);
    public int getLeaveListTotal(Leave leave);
    public boolean addLeave(Leave leave);

}
