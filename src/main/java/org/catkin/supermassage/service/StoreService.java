package org.catkin.supermassage.service;

import org.catkin.supermassage.entity.Store;
import org.catkin.supermassage.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author cakin
 *
 */
@Service
public class StoreService {
	@Autowired
	private StoreRepository storeRepository;

	public Store getStoreById(Long id) {
		
		return storeRepository.getStoreById(id);
	}
}
