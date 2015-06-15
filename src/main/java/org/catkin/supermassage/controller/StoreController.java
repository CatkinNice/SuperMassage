package org.catkin.supermassage.controller;

import java.util.List;

import org.catkin.supermassage.entity.Store;
import org.catkin.supermassage.entity.StoreBuy;
import org.catkin.supermassage.entity.model.PageResult;
import org.catkin.supermassage.entity.model.QueryParam;
import org.catkin.supermassage.service.StoreBuyService;
import org.catkin.supermassage.service.StoreService;
import org.catkin.supermassage.utils.ConstantsStatus;
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
	private StoreService ss;
	
	@Autowired
	private StoreBuyService sbs;
	
	/**
	 * 注：Content-Type：application/json;charset=UTF-8
	 */
	@RequestMapping(method = RequestMethod.PUT)
	public Store addOrEditStore(@RequestBody Store store) throws Exception {
		return ss.addOrEditStore(store);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public PageResult getStores(@RequestParam(required = false) String key, 
			@RequestParam(required = false) Integer page, 
			@RequestParam(required = false) Integer size) {
		return ss.getStores(new QueryParam(key, page, size));
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Store getStoreById(@PathVariable long id) {
		return ss.getStoreById(id);
	}
	
	@RequestMapping(value = "/buy", method = RequestMethod.PUT)
	public StoreBuy addStoreBuy(@RequestBody StoreBuy storeBuy) throws Exception {
		sbs.addStoreBuy(storeBuy);
		return storeBuy;
	}
	
	@RequestMapping(value = "/{id}/buy", method = RequestMethod.GET)
	public List<StoreBuy> getStoreBuys(@PathVariable("id") long storeId) throws Exception {
		return sbs.getStoreBuys(storeId);
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Store storeLogin(@RequestBody Store store) throws Exception {
		return ss.storeLogin(store);
	}
	
	@RequestMapping(value = "/changepwd", method = RequestMethod.PUT)
	public String changePwd(@RequestBody Store store) throws Exception {
		ss.changePwd(store);
		return ConstantsStatus.SUCCESS;
	}
}
