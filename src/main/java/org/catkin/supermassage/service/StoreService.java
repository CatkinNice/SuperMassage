package org.catkin.supermassage.service;

import org.catkin.supermassage.dao.StoreRepository;
import org.catkin.supermassage.entity.Store;
import org.catkin.supermassage.utils.RESTurl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 店铺服务
 * @author Catkin_nice
 *
 */
@RestController
@RequestMapping(RESTurl.store)
public class StoreService {
	
	@Autowired
	private StoreRepository storeMapper;
	
	@RequestMapping(method = RequestMethod.GET)
	public String getStores() {
		return "this is store!";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String getStoreById(@PathVariable Long id) {
		Store store = new StoreRepository().getStoreById(id);
		return id.toString();
	}
}
