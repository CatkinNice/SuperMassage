package org.catkin.supermassage.controller;

import java.util.List;

import org.catkin.supermassage.entity.Roome;
import org.catkin.supermassage.service.RoomeService;
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
 * 
 * @author Catkin_nice
 *
 */
@RestController
@RequestMapping(RESTurl.roome)
public class RoomeController {
	@Autowired
	private RoomeService rs;
	
	@RequestMapping(method = RequestMethod.PUT)
	public Roome addRoome(@RequestBody Roome roome) throws Exception {
		rs.addRoome(roome);
		return roome;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Roome> getRoomes(@RequestParam long storeid, 
			@RequestParam(required = false) Integer status) {
		return rs.getRoomes(storeid, status);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public String getRoomes(@PathVariable long id, 
			@RequestParam int status, 
			@RequestParam(required = false) Long et) {
		rs.updateRoomeById(id, status, et);
		return ConstantsStatus.SUCCESS;
	}
}
