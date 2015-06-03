package org.catkin.supermassage.service;

import java.util.List;

import org.catkin.supermassage.entity.Staff;
import org.catkin.supermassage.repository.StaffRepository;
import org.catkin.supermassage.utils.Sequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author Catkin_nice
 *
 */
@Service
public class StaffService {
	@Autowired
	private StaffRepository sr;

	public void addOrEditStaff(Staff staff) {
		if (staff.getId() == null) {
			staff.setId(Sequence.getNextId());
		}
		sr.addOrEditStaff(staff);
	}
	
	public List<Staff> getStaffs(Staff staff) {
		return sr.getStaffs(staff);
	}

	public Staff getStaffById(String id) {
		return sr.getStaffById(id);
	}
	
	
}
