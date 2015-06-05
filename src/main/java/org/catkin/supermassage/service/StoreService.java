package org.catkin.supermassage.service;

import java.util.List;

import org.catkin.supermassage.entity.Store;
import org.catkin.supermassage.entity.StoreBuy;
import org.catkin.supermassage.entity.model.PageResult;
import org.catkin.supermassage.entity.model.QueryParam;
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
		if (sr.checkSameAccount(store.getAccount())) {
			throw new LogicException(ErrorType.errorSameAccount);
		}
		if (sr.checkSameName(store.getName())) {
			throw new LogicException(ErrorType.errorSameStore);
		}
		
		boolean isAdd = false;
		if (store.getId() == null) {
			isAdd = true;
			store.setId(Sequence.getNextId());
		}
		
		sr.addOrEditStore(store);
		store.setPwd(null);			//返回时清空密码
		
		if (isAdd && store.getStoreBuy() != null){
			StoreBuy storeBuy = store.getStoreBuy();
			storeBuy.setId(Sequence.getNextId());
			storeBuy.setStoreId(store.getId());
			sbr.addStoreBuy(storeBuy);
			store.setStaffNum(storeBuy.getStaffNum());
		}
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

	public Store storeLogin(Store store) {
		Store dbStore = sr.getStoreByAccount(store.getAccount());
		if (dbStore == null) {
			throw new LogicException(ErrorType.errorNotAccount);
		}
		
		if (!dbStore.getPwd().equals(store.getPwd())) {
			throw new LogicException(ErrorType.errorPassword);
		}
		
		//返回时清空密码
		dbStore.setPwd(null);
		return dbStore;
	}

	public void changePwd(Store store) {
		Store dbStore = sr.getStoreById(store.getId(), true);
		if (!dbStore.getPwd().equals(store.getPwd())) {
			throw new LogicException(ErrorType.errorPassword);
		}
		sr.changePwd(store);
	}
}
