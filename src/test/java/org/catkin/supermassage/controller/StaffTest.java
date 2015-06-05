package org.catkin.supermassage.controller;

import java.util.Arrays;

import org.junit.Test;
import org.catkin.supermassage.BaseTest;
import org.catkin.supermassage.entity.Staff;
import org.catkin.supermassage.entity.model.PageResult;
import org.catkin.supermassage.utils.Json;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author Catkin_nice
 *
 */
public class StaffTest extends BaseTest {
	@Autowired
	StaffController sc;
	
	@Test
	public void addOrEditStaff() throws Exception {
		Staff staff = new Staff();
		staff.setId(6001L);
		staff.setStoreId(1001L);
		staff.setName("李四狗");
		staff.setExperience(5);
		staff.setJob("按摩师");
		staff.setPackages(Arrays.asList(3001L, 4001L));
		
		staff = sc.addOrEditStaff(Json.toJson(staff));
		System.out.println(Json.toJson(staff));
	}
	
	@Test
	public void getStaffs() throws Exception {
		Staff staff = new Staff();
		staff.setStoreId(8001L);
		staff.setKey("足浴");
		staff.setWokeStatus(-1);
		staff.setPageParam(0, 1);
		
		PageResult result = sc.getStaffs(Json.toJson(staff));
		System.err.println(Json.toJson(result));
	}
	
	@Test
	public void getStaff() throws Exception {
		Staff staff = sc.getStaff(1);
		System.err.println(Json.toJson(staff));
	}
	
	@Test
	public void delStaff() throws Exception {
		sc.delStaff(5001);
	}
}
