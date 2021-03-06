package org.catkin.supermassage.controller;

import org.catkin.supermassage.entity.Staff;
import org.catkin.supermassage.entity.model.PageResult;
import org.catkin.supermassage.service.StaffService;
import org.catkin.supermassage.utils.ConstantsStatus;
import org.catkin.supermassage.utils.Json;
import org.catkin.supermassage.utils.RESTurl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 员工服务
 * @author Catkin_nice
 *
 */
@RestController
@RequestMapping(RESTurl.staff)
public class StaffController {
	
	@Autowired
	private StaffService ss;
	
	@RequestMapping(method = RequestMethod.PUT)
	public Staff addOrEditStaff(@RequestBody Staff staff) throws Exception {
		ss.addOrEditStaff(staff);
		return staff;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public PageResult getStaffs(@RequestBody String json) throws Exception {
		return ss.getStaffs(Json.parse(json, Staff.class));
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Staff getStaff(@PathVariable long id) {
		return ss.getStaffById(id);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String delStaff(@PathVariable long id) {
		ss.delStaffById(id);
		return ConstantsStatus.SUCCESS;
	}
}
