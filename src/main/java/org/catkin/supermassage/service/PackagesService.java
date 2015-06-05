package org.catkin.supermassage.service;

import java.util.ArrayList;
import java.util.List;

import org.catkin.supermassage.entity.Packages;
import org.catkin.supermassage.entity.StaffPackages;
import org.catkin.supermassage.entity.model.PageResult;
import org.catkin.supermassage.repository.PackagesRepository;
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
public class PackagesService {
	
	@Autowired
	private PackagesRepository pr;

	@Autowired
	private StaffPackagesRepository spr;

	public void addOrEditPackage(Packages packages) {
		if (packages.getId() == null) {
			packages.setId(Sequence.getNextId());
		}
		pr.addOrEditPackage(packages);
		
		//给项目添加可服务员工
		if (!CollectionUtils.isEmpty(packages.getStaffs())) {
			Long packagesId = packages.getId();
			spr.delStaffPackagesByPackageId(packagesId);
			List<StaffPackages> staffPackages = new ArrayList<StaffPackages>();
			
			for (Long staffId : packages.getStaffs()) {
				staffPackages.add(new StaffPackages(staffId, packagesId));
			}
			spr.addStaffPackages(staffPackages);
		}
	}

	public PageResult getPackages(Packages packares) {
		List<Packages> data = pr.getPackages(packares);
		int totalSize = pr.getPackagesCount(packares);
		return new PageResult(data, totalSize);
	}

	public Packages getPackage(Long id) {
		return pr.getPackage(id);
	}

	public void delPackage(Long id) {
		pr.delPackage(id);	
	}
	
	
}
