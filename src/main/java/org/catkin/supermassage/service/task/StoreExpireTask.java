package org.catkin.supermassage.service.task;

import java.util.List;

import org.catkin.supermassage.utils.Log;
import org.catkin.supermassage.service.jms.JmsSender;
import org.catkin.supermassage.service.jms.MsgType;
import org.catkin.supermassage.entity.StoreBuy;
import org.catkin.supermassage.entity.model.MsgInfo;
import org.catkin.supermassage.repository.StoreBuyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class StoreExpireTask {
	@Autowired
	private StoreBuyRepository sbr;
	
	@Autowired
	private JmsSender jmsSender; 
	
	/**
	 * 每天0点执行一次
	 */
	@Scheduled(cron = "0 0 0 * * ?")
	public void run() {
		List<StoreBuy> list = sbr.getExpStoreBuy();
		MsgInfo<Object> msgInfo = new MsgInfo<Object>();
		msgInfo.setType(MsgType.storeExp);
		
		for (StoreBuy storeBuy : list) {
			msgInfo.setBody(storeBuy);
			msgInfo.setReceiver(storeBuy.getStoreId());
			jmsSender.sendMessage(msgInfo);
		}
		Log.info("[StoreExpireTask] ExpireBuyNum:" + list.size());
	}
}
