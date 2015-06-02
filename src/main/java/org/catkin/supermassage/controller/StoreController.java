package org.catkin.supermassage.controller;

import java.util.List;

import org.catkin.supermassage.entity.Store;
import org.catkin.supermassage.service.StoreService;
import org.catkin.supermassage.utils.Json;
import org.catkin.supermassage.utils.RESTurl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@RequestMapping(method = RequestMethod.PUT)
	public Store addOrEditStore(@RequestBody String json) throws Exception {
		Store store = Json.parse(json, Store.class);
		return storeService.addOrEditStore(store);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Store> getStores(@RequestParam(required = false) String key, 
			@RequestParam(required = false) Integer from, 
			@RequestParam(required = false) Integer size) {
		return storeService.getStores(key, from, size);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Store getStoreById(@PathVariable Long id) {
		return storeService.getStoreById(id);
	}
	
	@RequestMapping(value = "/{id}/info", method = RequestMethod.GET)
	public Store getStoreInfoById(@PathVariable Long id) {
		return storeService.getStoreById(id);
	}
}
