package org.catkin.supermassage.service;

import org.catkin.supermassage.entity.Store;
import org.catkin.supermassage.repository.StoreRepository;
import org.catkin.supermassage.utils.Sequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author Catkin_nice
 *
 */
@Service
public class StoreService {
	@Autowired
	private StoreRepository sr;
	
	public Store addOrEditStore(Store store) {
		if (store.getId() == null) {
			store.setId(Sequence.getNextId());
		}
		sr.addOrEditStore(store);
		return store;
	}

	public Store getStoreById(Long id) {
		return sr.getStoreById(id);
	}

	public void delStoreById(Long id) {
		
	}
}
