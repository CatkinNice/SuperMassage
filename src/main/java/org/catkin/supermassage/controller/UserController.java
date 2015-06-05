package org.catkin.supermassage.controller;

import org.catkin.supermassage.utils.ConstantsStatus;
import org.catkin.supermassage.utils.Json;
import org.catkin.supermassage.utils.RESTurl;
import org.catkin.supermassage.entity.User;
import org.catkin.supermassage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping(RESTurl.user)
public class UserController {
	
	@Autowired
	private UserService us;
	
	@RequestMapping(method = RequestMethod.PUT)
	public User addOrEditUser(@RequestBody String json) throws Exception {
		User user = Json.parse(json, User.class);
		us.addOrEditUser(user);
		return user;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public User getUser(@PathVariable long id) throws Exception {
		return us.getUserById(id);
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public User login(@RequestBody String json) throws Exception {
		User user = Json.parse(json, User.class);
		return us.login(user);
	}
	
	@RequestMapping(value = "/changepwd", method = RequestMethod.POST)
	public String changepwd(@RequestBody String json) throws Exception {
		us.changepwd(Json.parse(json, User.class));
		return ConstantsStatus.SUCCESS;
	}
}
