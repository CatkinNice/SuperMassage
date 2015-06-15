package org.catkin.supermassage.controller;

import org.catkin.supermassage.entity.Consume;
import org.catkin.supermassage.service.ConsumeService;
import org.catkin.supermassage.utils.ConstantsStatus;
import org.catkin.supermassage.utils.RESTurl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author Catkin_nice
 *
 */
@RestController
@RequestMapping(RESTurl.consume)
public class ConsumeController {
	
	@Autowired
	private ConsumeService sc;
	
	@RequestMapping(method = RequestMethod.PUT)
	public String addOrEditConsume(@RequestBody Consume consume) throws Exception {
		sc.addOrEditConsume(consume);
		return ConstantsStatus.SUCCESS;
	}
	
	@RequestMapping(value = "/checkplan", method = RequestMethod.POST)
	public String checkPlan(@RequestBody Consume consume) throws Exception {
		return sc.checkPlan(consume);
	}

}
