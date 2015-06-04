package org.catkin.supermassage.controller;

import org.catkin.supermassage.BaseTest;
import org.catkin.supermassage.entity.Packages;
import org.catkin.supermassage.entity.PageResult;
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
		packages.setStoreId(8001L);
		packages.setName("神仙提神去寒按摩");
		packages.setStorePrice(128);
		packages.setAppPrice(108);
		packages.setTimed(60);
		
		packages = pc.addOrEditPackage(Json.toJson(packages));
		System.err.println(Json.toJson(packages));
	}
	
	@Test
	public void getPackages() throws Exception {
		Packages packages = new Packages();
		packages.setStoreId(8001L);
		packages.setKey("仙提");
		packages.setPageParam(0, 10);
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
		pc.delPackage(10001);
	}
}
