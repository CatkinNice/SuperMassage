package org.catkin.supermassage.service;

import java.util.List;

import org.catkin.supermassage.entity.PageResult;
import org.catkin.supermassage.entity.Staff;
import org.catkin.supermassage.repository.StaffRepository;
import org.catkin.supermassage.repository.StoreBuyRepository;
import org.catkin.supermassage.utils.ErrorType;
import org.catkin.supermassage.utils.LogicException;
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
	
	@Autowired
	private StoreBuyRepository sbr;

	public void addOrEditStaff(Staff staff) {
		if (staff.getId() == null) {
			Integer staffNum = sbr.getStaffNum(staff.getId());
			Integer usedStaffNum = sr.getStaffNumByStoreId(staff.getStoreId());
			if (staffNum > usedStaffNum) {
				staff.setId(Sequence.getNextId());
			} else {
				throw new LogicException(ErrorType.errorStaffOver);
			}
		}
		
		//TODO 添加员工可服务项目
		sr.addOrEditStaff(staff);
	}
	
	public PageResult getStaffs(Staff staff) {
		List<Staff> data = sr.getStaffs(staff);
		int totalSize = sr.getStaffsCount(staff);
		return new PageResult(data, totalSize);
	}

	public Staff getStaffById(String id) {
		return sr.getStaffById(id);
	}
	
	
}
