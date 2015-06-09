package org.catkin.supermassage.service;

import java.util.ArrayList;
import java.util.List;

import org.catkin.supermassage.entity.Staff;
import org.catkin.supermassage.entity.StaffPackages;
import org.catkin.supermassage.entity.model.PageResult;
import org.catkin.supermassage.repository.StaffPackagesRepository;
import org.catkin.supermassage.repository.StaffRepository;
import org.catkin.supermassage.repository.StoreBuyRepository;
import org.catkin.supermassage.utils.ConstantsStatus;
import org.catkin.supermassage.utils.ErrorType;
import org.catkin.supermassage.utils.LogicException;
import org.catkin.supermassage.utils.Sequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
	
	@Autowired
	private StaffPackagesRepository spr;

	public void addOrEditStaff(Staff staff) {
		if (staff.getId() == null) {
			Integer staffNum = sbr.getStaffNum(staff.getStoreId());
			Integer usedStaffNum = sr.getStaffNumByStoreId(staff.getStoreId());
			if (staffNum != null && staffNum > usedStaffNum) {
				staff.setId(Sequence.getNextId());
			} else {
				throw new LogicException(ErrorType.errorStaffOver);
			}
		}
		
		if (staff.getWokeStatus() == null) {
			staff.setWokeStatus(ConstantsStatus.Staff.WOKE_STATUS_IDLE);
		}
		sr.addOrEditStaff(staff);
		
		//为员工添加可服务项目
		if (!CollectionUtils.isEmpty(staff.getPackages())) {
			Long staffId = staff.getId();
			spr.delStaffPackagesByStaffId(staffId);
			List<StaffPackages> staffPackages = new ArrayList<StaffPackages>();
			
			for (Long packagesId : staff.getPackages()) {
				staffPackages.add(new StaffPackages(staffId, packagesId));
			}
			spr.addStaffPackages(staffPackages);
		}
	}
	
	public PageResult getStaffs(Staff staff) {
		List<Staff> data = sr.getStaffs(staff);
		int totalSize = sr.getStaffsCount(staff);
		return new PageResult(data, totalSize);
	}

	public Staff getStaffById(Long id) {
		return sr.getStaffById(id);
	}

	public void delStaffById(Long id) {
		sr.delStaffById(id);
	}
	
	
}
