package org.catkin.supermassage.service.task;

import org.catkin.supermassage.repository.OrderRepository;
import org.catkin.supermassage.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class StaffStatusTask {
	
	@Autowired
	private StaffRepository sr;
	
	@Autowired
	private OrderRepository or;
	
	/**
	 * 以一分钟的固定间隔更新员工服务状态
	 */
	@Scheduled(initialDelay = 1000 * 60, fixedRate = 1000 * 60)
	public void run() {
		
	}
}
