package org.catkin.supermassage.service;

import java.util.List;

import org.catkin.supermassage.entity.Consume;
import org.catkin.supermassage.entity.Order;
import org.catkin.supermassage.entity.Store;
import org.catkin.supermassage.entity.model.PageResult;
import org.catkin.supermassage.repository.ConsumeRepository;
import org.catkin.supermassage.repository.OrderRepository;
import org.catkin.supermassage.repository.StoreRepository;
import org.catkin.supermassage.utils.ConstantsStatus;
import org.catkin.supermassage.utils.Sequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author Catkin_nice
 *
 */
@Service
public class OrderService {
	
	@Autowired
	private OrderRepository or;
	
	@Autowired
	private StoreRepository sr;
	
	@Autowired
	private ConsumeRepository cr;
	
	/**
	 * 
	 * @param order
	 * @return 用户订单使用码
	 */
	public void addOrder(Order order) {
		order.setDeleted(ConstantsStatus.Order.DELETED_NO);
		order.setId(Sequence.getNextOrderId());
		
		if (order.getPayType() == null) {
			order.setPayType(ConstantsStatus.Order.PAY_TYPE_STORE);
		}
		
		if (order.getUseStatus() == null) {
			order.setUseStatus(ConstantsStatus.Order.USE_STATUS_NOTPAY);
		}
		
		or.addOrder(order);
	}

	public PageResult getOrder(Order order) {
		List<Order> data = or.getOrder(order);
		int totalSize = or.getOrderCount(order);
		return new PageResult(data, totalSize);
	}


	public Order getOrderById(String id) {
		Order order = or.getOrderById(id);
		
		Store store = sr.getStoreById(order.getStoreId());
		order.setStoreName(store.getName());
		
		Consume consume = cr.getConsumeByOrderId(id);
		order.setConsume(consume);
		
		return order;
	}


	public void delOrderById(String id) {
		or.editOrder(new Order(id, ConstantsStatus.Order.DELETED_YES));
	}


	public void payOrder(Order order) {
		or.editOrder(order);		
	}

}
