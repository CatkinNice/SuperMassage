package org.catkin.supermassage.repository;

import org.junit.Test;
import org.catkin.supermassage.BaseTest;
import org.catkin.supermassage.entity.Store;
import org.springframework.beans.factory.annotation.Autowired;

public class StoreRepositoryTest extends BaseTest {
	
	@Autowired
	private StoreRepository sr;
	
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
		sr.insertOrUpdateStore(store);
	}
	
	
}

