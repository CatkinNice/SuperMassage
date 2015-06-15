package org.catkin.supermassage.controller;

import org.catkin.supermassage.entity.Packages;
import org.catkin.supermassage.entity.model.PageResult;
import org.catkin.supermassage.service.PackagesService;
import org.catkin.supermassage.utils.ConstantsStatus;
import org.catkin.supermassage.utils.RESTurl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 服务项目（商品）
 * @author Catkin_nice
 *
 */
@RestController
@RequestMapping(RESTurl.packages)
public class PackagesController {
	
	@Autowired
	private PackagesService ps;
	
	@RequestMapping(method = RequestMethod.PUT)
	public Packages addOrEditPackage(@RequestBody Packages packages) throws Exception {
		ps.addOrEditPackage(packages);
		return packages;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public PageResult getPackages(@RequestBody Packages packages) throws Exception {
		return ps.getPackages(packages);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Packages getPackage(@PathVariable long id) {
		return ps.getPackage(id);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String delPackage(@PathVariable long id) {
		ps.delPackage(id);
		return ConstantsStatus.SUCCESS;
	}
}
