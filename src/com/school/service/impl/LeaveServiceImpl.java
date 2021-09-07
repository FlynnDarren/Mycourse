package com.school.service.impl;

import com.school.entiey.Leave;
import com.school.entiey.Page;
import com.school.repository.impl.LeaveRepositoryImpl;
import com.school.service.LeaveService;

import java.util.List;

public class LeaveServiceImpl implements LeaveService {
    LeaveRepositoryImpl leaveRepositoryImpl = new LeaveRepositoryImpl();

    @Override
    public boolean deleteLeave(int id) {
    boolean leave = leaveRepositoryImpl.deleteLeave(id);
        leaveRepositoryImpl.closeCon();
        return leave;
    }

    @Override
    public boolean editLeave(Leave leave) {
     boolean lea = leaveRepositoryImpl.editLeave(leave);
        leaveRepositoryImpl.closeCon();
        return lea;
    }

    @Override
    public List<Leave> getLeaveList(Leave leave, Page page) {
        List<Leave> leaveList = leaveRepositoryImpl.getLeaveList(leave, page);
        leaveRepositoryImpl.closeCon();
        return  leaveList;
    }

    @Override
    public int getLeaveListTotal(Leave leave) {
        int total = leaveRepositoryImpl.getLeaveListTotal(leave);
        leaveRepositoryImpl.closeCon();
        return total;
    }

    @Override
    public boolean addLeave(Leave leave) {
      boolean add =  leaveRepositoryImpl.addLeave(leave);
        leaveRepositoryImpl.closeCon();
        return add;
    }
}
