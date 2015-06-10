package org.catkin.supermassage.controller;

import java.util.Arrays;

import org.catkin.supermassage.BaseTest;
import org.catkin.supermassage.entity.Packages;
import org.catkin.supermassage.entity.model.PageResult;
import org.catkin.supermassage.utils.Json;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author Catkin_nice
 *
 */
public class PackagesTest extends BaseTest {
	@Autowired
	private PackagesController pc;
	
	@Test
	public void addOrEditPackage() throws Exception {
		Packages packages = new Packages();
		packages.setId(8001L);
		packages.setStoreId(1001L);
		packages.setName("霸王浴足");
		packages.setStorePrice(98);
		packages.setAppPrice(88);
		packages.setTimed(60);
		packages.setStaffs(Arrays.asList(2001L));
		
		packages = pc.addOrEditPackage(Json.toJson(packages));
		System.err.println(Json.toJson(packages));
	}
	
	@Test
	public void getPackages() throws Exception {
		Packages packages = new Packages();
		packages.setStoreId(8001L);
		packages.setKey("仙提");
		packages.setFrom(0);
		packages.setSize(10);
		PageResult result = pc.getPackages(Json.toJson(packages));
		System.err.println(Json.toJson(result));
	}
	
	@Test
	public void getPackage() throws Exception {
		Packages packages = pc.getPackage(10001);
		System.err.println(Json.toJson(packages));
	}
	
	@Test
	public void delPackage() throws Exception {
		pc.delPackage(7001);
	}
}
