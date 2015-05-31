package org.catkin.supermassage.service;

import org.catkin.supermassage.dao.StoreDao;
import org.catkin.supermassage.entity.Store;
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
public class StoreService {
	
	@Autowired
	private StoreDao storeMapper;
	
	@RequestMapping(method = RequestMethod.GET)
	public String getStores() {
		Log.logger.debug("==============================log====");
		
		return "this is store!";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Store getStoreById(@PathVariable Long id) {
		Store store = storeMapper.getStoreById(id);
		return store;
	}
}
