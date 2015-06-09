package org.catkin.supermassage.service.task;

import org.catkin.supermassage.repository.StoreBuyRepository;
import org.catkin.supermassage.utils.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class StoreExpireTask {
	@Autowired
	private StoreBuyRepository sbr;
	
	/**
	 * 每天0点执行一次
	 */
	@Scheduled(cron = "0 0 0 * * ?")
	public void run() {
		Log.info("run StoreExpireTask...");
		//TODO 添加消息机制
	}
}
