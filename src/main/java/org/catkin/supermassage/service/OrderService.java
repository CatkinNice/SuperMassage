package org.catkin.supermassage.service;

import org.catkin.supermassage.entity.Order;
import org.catkin.supermassage.repository.OrderRepository;
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
	
	/**
	 * 
	 * @param order
	 * @return 用户订单使用码
	 */
	public void addOrder(Order order) {
		order.setDeleted(ConstantsStatus.ORDER_DELETED_NO);
		order.setId(Sequence.getNextOrderId());
		
		if (order.getPayType() == null) {
			order.setPayType(ConstantsStatus.ORDER_PAYTYPE_STORE);
		}
		
		if (order.getUseStatus() == null) {
			order.setUseStatus(ConstantsStatus.ORDER_USESTATUS_NOTPAY);
		}
		
		or.addOrder(order);
	}


	public Order getOrderById(String id) {
//		OrderInfoResult result =  or.getOrderById(id);
		return null;
	}
	
}
