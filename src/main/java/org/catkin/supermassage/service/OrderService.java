package org.catkin.supermassage.service;

import org.catkin.supermassage.entity.Consume;
import org.catkin.supermassage.entity.Order;
import org.catkin.supermassage.repository.ConsumeRepository;
import org.catkin.supermassage.repository.OrderRepository;
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
	private ConsumeRepository cr;

	/**
	 * 
	 * @param order
	 * @return 用户订单使用码
	 */
	public String addOrder(Order order) {
		order.setDeleted(0);
		order.setId(Sequence.getNextOrderId());
		
		if (order.getPayType() == null) {
			order.setPayType(0);
		}
		
		if (order.getUseStatus() == null) {
			order.setUseStatus(0);
		}
		
		or.addOrder(order);
		cr.addOrEditConcume(new Consume(order.getId(), order.getPackages().getTimed()));
		return order.getId().substring(0, 6);
	}


	public Order getOrderById(String id) {
//		OrderInfoResult result =  or.getOrderById(id);
		return null;
	}
	
}
