package org.catkin.supermassage.controller;

import java.util.Calendar;
import java.util.List;

import org.catkin.supermassage.BaseTest;
import org.catkin.supermassage.entity.StoreBuy;
import org.catkin.supermassage.utils.Json;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author Catkin_nice
 *
 */
public class StoreTest extends BaseTest {
	@Autowired
	StoreController sc;
	
	@Test
	public void addStoreBuy() throws Exception {
		StoreBuy buy = new StoreBuy();
		buy.setStoreId(1L);
		buy.setStaffNum(100);
		buy.setPrice(100000);
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(2015, 5, 3);
		buy.setStartTime(calendar.getTime());
		calendar.set(2016, 5, 3, 23, 59, 59);
		buy.setEndTime(calendar.getTime());
		buy = sc.addStoreBuy(Json.toJson(buy));
		System.out.println(Json.toJson(buy));
	}
	
	@Test
	public void getStoreBuys() throws Exception {
		List<StoreBuy> list = sc.getStoreBuys(5);
		System.out.println(list.get(0).getPrice());
	}
}
