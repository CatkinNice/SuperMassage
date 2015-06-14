package org.catkin.supermassage.service;

import java.util.List;

import org.catkin.supermassage.entity.StoreBuy;
import org.catkin.supermassage.repository.StoreBuyRepository;
import org.catkin.supermassage.utils.Sequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author Catkin_nice
 *
 */
@Service
public class StoreBuyService {
	@Autowired
	private StoreBuyRepository sbr;

	public void addStoreBuy(StoreBuy storeBuy) {
		storeBuy.setId(Sequence.getNextId());
		sbr.addStoreBuy(storeBuy);
		//重新购买服务时将10天内过期服务消息关闭
		sbr.editExpiredMsg(storeBuy.getStoreId());
	}

	public List<StoreBuy> getStoreBuys(Long id) {
		return sbr.getStoreBuyByStoreId(id);
	}
	
	
}
