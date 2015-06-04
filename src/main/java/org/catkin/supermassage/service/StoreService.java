package org.catkin.supermassage.service;

import java.util.List;

import org.catkin.supermassage.entity.PageResult;
import org.catkin.supermassage.entity.QueryParam;
import org.catkin.supermassage.entity.Store;
import org.catkin.supermassage.repository.StoreBuyRepository;
import org.catkin.supermassage.repository.StoreRepository;
import org.catkin.supermassage.utils.ErrorType;
import org.catkin.supermassage.utils.LogicException;
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
	
	@Autowired
	private StoreBuyRepository sbr;
	
	public Store addOrEditStore(Store store) {
		if (sr.checkSameStore(store.getName())) {
			throw new LogicException(ErrorType.errorSameStore);
		}
		
		if (store.getId() == null) {
			store.setId(Sequence.getNextId());
		}
		
		sr.addOrEditStore(store);
		return store;
	}

	public Store getStoreById(Long id) {
		Store store = sr.getStoreById(id);
		store.setStaffNum(sbr.getStaffNum(id));
		return store;
	}

	public PageResult getStores(QueryParam param) {
		List<Store> data = sr.getStores(param);
		int totalSize = sr.getStoresCount(param);
		return new PageResult(data, totalSize);
	}
}
