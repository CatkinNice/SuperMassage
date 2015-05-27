package org.catkin.supermassage.service;

import org.catkin.supermassage.utils.RESTurl;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 员工服务
 * @author Catkin_nice
 *
 */
@RestController
@EnableAutoConfiguration
@RequestMapping(RESTurl.staff)
public class StaffService {
	
	@RequestMapping(method = RequestMethod.GET)
	public String getStaffs() {
		return "this is staff!...";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String getStaff(@PathVariable String id) {
		return "this is staff!..." + id;
	}
}
