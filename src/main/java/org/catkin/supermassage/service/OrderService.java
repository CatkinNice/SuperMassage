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


	public Order getOrderById(String id) {
		or.getOrderById(id);
		return null;
	}


	public void delOrderById(String id) {
		or.editOrder(new Order(id, ConstantsStatus.Order.DELETED_YES));
	}


	public void payOrder(Order order) {
		or.editOrder(order);		
	}
	
}
