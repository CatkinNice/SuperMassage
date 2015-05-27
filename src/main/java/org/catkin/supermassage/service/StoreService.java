package org.catkin.supermassage.service;

import org.catkin.supermassage.utils.RESTurl;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 店铺服务
 * @author Catkin_nice
 *
 */
@RestController
@EnableAutoConfiguration
@RequestMapping(RESTurl.store)
public class StoreService {
	
	@RequestMapping(method = RequestMethod.GET)
	public String getStores() {
		return "this is store!";
	}
}
