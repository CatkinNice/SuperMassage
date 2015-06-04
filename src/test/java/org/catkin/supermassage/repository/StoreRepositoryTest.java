package org.catkin.supermassage.repository;

import java.util.List;

import org.junit.Test;
import org.catkin.supermassage.BaseTest;
import org.catkin.supermassage.entity.Store;
import org.catkin.supermassage.entity.StoreBuy;
import org.springframework.beans.factory.annotation.Autowired;

public class StoreRepositoryTest extends BaseTest {
	
	@Autowired
	private StoreRepository sr;

	@Autowired
	private StoreBuyRepository sbr;
	
	@Test
	public void getStoreById() {
		Store store = sr.getStoreById(1L);
		System.out.println(store.getName());
	}
	
	@Test
	public void addStore() {
		Store store = new Store();
		store.setId(3L);
		store.setName("这是第三家店");
		store.setPwd("987654321");
		store.setLongLatItude("127,168");
		sr.addOrEditStore(store);
	}
	
	@Test
	public void getExpStoreBuy() {
		List<StoreBuy> list = sbr.getExpStoreBuy();
		for (StoreBuy storeBuy : list) {
			System.out.println("buyTime===" + storeBuy.getBuyTime());
			System.out.println("endTime===" + storeBuy.getEndTime());
		}
	}
	
	@Test
	public void getStaffNum() {
		Integer staffNum = sbr.getStaffNum(2001L);
		System.out.println("staffNum===" + staffNum);
	}
	
}

