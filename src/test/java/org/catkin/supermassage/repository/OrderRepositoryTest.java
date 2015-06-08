package org.catkin.supermassage.repository;

import java.util.Date;

import org.catkin.supermassage.BaseTest;
import org.catkin.supermassage.entity.Order;
import org.catkin.supermassage.entity.Packages;
import org.catkin.supermassage.entity.Staff;
import org.catkin.supermassage.utils.Sequence;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderRepositoryTest extends BaseTest {
	
	@Autowired
	private OrderRepository or;
	
	@Test
	public void addOrEditOrder() {
		Order order = new Order();
		order.setId(Sequence.getNextOrderId());
		order.setStoreId(10001L);
		order.setUserId(13001L);
		order.setPayType(0);
		order.setUseStatus(0);
		order.setDeleted(0);
		
		Packages packages = new Packages();
		packages.setId(12001L);
		packages.setName("霸王浴足");
		packages.setStorePrice(98);
		packages.setAppPrice(88);
		packages.setTimed(50);
		order.setPackages(packages);
		
		Staff staff = new Staff();
		staff.setId(11001L);
		staff.setName("二狗");
		or.addOrder(order);
	}
}
