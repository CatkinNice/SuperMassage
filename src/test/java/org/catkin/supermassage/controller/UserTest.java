package org.catkin.supermassage.controller;

import org.catkin.supermassage.BaseTest;
import org.catkin.supermassage.entity.User;
import org.catkin.supermassage.utils.Json;
import org.catkin.supermassage.utils.Sequence;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserTest extends BaseTest {
	@Autowired
	UserController uc;
	
	@Test
	public void addOrEditUser() throws Exception {
		User user = new User();
		user.setId(Sequence.getNextId());
		user.setAccount("catkin");
		user.setPwd("123");
		uc.addOrEditUser(user);
		System.out.println(Json.toJson(user));
	}
}
