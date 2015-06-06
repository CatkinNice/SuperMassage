package org.catkin.supermassage.repository;

import java.util.ArrayList;
import java.util.List;

import org.catkin.supermassage.BaseTest;
import org.catkin.supermassage.entity.StaffPackages;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author Catkin_nice
 *
 */
public class StaffPackagesRepositoryTest extends BaseTest {
	@Autowired
	private StaffPackagesRepository spr;
	
	@Test
	public void addStaffPackages() {
		List<StaffPackages> sps = new ArrayList<StaffPackages>();
		sps.add(new StaffPackages(1, 1));
		sps.add(new StaffPackages(1, 2));
		sps.add(new StaffPackages(2, 2));
		spr.addStaffPackages(sps);
	}
}
