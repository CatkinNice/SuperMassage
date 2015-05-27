package org.catkin.supermassage;

import org.catkin.supermassage.service.StaffService;
import org.catkin.supermassage.service.StoreService;
import org.springframework.boot.SpringApplication;

/**
 * 
 * @author Catkin_nice
 *
 */
public class App 
{
	public static void main(String[] args) {
		Class<?>[] sources = { StoreService.class, StaffService.class };
		SpringApplication.run(sources, args);
	}
}
