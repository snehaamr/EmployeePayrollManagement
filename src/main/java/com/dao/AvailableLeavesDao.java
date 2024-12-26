package com.siddhrans.biometric.dao;

import java.util.List;

import com.siddhrans.biometric.model.LeaveTypes;
import com.siddhrans.biometric.model.LeavesAvailable;
import com.siddhrans.biometric.model.User;

public interface AvailableLeavesDao {
	
	LeavesAvailable findAvailableLeavesByUserAndType(User user, LeaveTypes leaveTypes);
    
    void saveLeavesAvailable(LeavesAvailable leavesAvailable);
    
    void updateLeavesAvailable(LeavesAvailable leavesAvailable);
     
    void deleteLeavesAvailable(LeavesAvailable leavesAvailable);
    
    List<LeavesAvailable> findAvailableLeavesByUser(User user);
}