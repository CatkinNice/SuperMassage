package org.catkin.supermassage.service;

import org.catkin.supermassage.entity.Order;
import org.catkin.supermassage.repository.OrderRepository;
import org.catkin.supermassage.utils.ErrorType;
import org.catkin.supermassage.utils.LogicException;
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

	public void addOrEditOrder(Order order) {
		if (order.getId() == null) {
			order.setId(Sequence.getNextId());
		}
		
		if (order.getPlanStaffId() != null && order.getPlanTime() != null) {
			if (!or.checkPlanTime(order.getPlanStaffId(), order.getPlanTime())) {
				throw new LogicException(ErrorType.errorPlanTime);
			}
		}
		
		or.addOrEditOrder(order);
	}
	
}
