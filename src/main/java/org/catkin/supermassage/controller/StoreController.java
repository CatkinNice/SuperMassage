package org.catkin.supermassage.controller;

import org.catkin.supermassage.entity.Store;
import org.catkin.supermassage.service.StoreService;
import org.catkin.supermassage.utils.Log;
import org.catkin.supermassage.utils.RESTurl;
import org.springframework.beans.factory.annotation.Autowired;
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
public class StoreController {
	
	@Autowired
	private StoreService storeService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String getStores() {
		Log.logger.error("==============================LOG===");
		
		return "this is store!";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Store getStoreById(@PathVariable Long id) {
		Store store = storeService.getStoreById(id);
		return store;
	}
}
