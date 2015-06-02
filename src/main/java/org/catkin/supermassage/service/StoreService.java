package org.catkin.supermassage.service;

import java.util.List;

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

	public List<Store> getStores(String key, Integer from, Integer size) {
		return sr.getStores(key, from, size);
	}
}
