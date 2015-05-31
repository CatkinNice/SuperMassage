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
		System.out.println("OK=============================" + store.getName());
	}
	
	
}

