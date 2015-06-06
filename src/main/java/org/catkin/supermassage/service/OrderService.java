package org.catkin.supermassage.service;

import org.catkin.supermassage.entity.Order;
import org.catkin.supermassage.repository.OrderRepository;
import org.catkin.supermassage.repository.StaffRepository;
import org.catkin.supermassage.utils.ConstantsStatus;
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
	
	@Autowired
	private StaffRepository sr;

	public String addOrEditOrder(Order order) {
		if (order.getId() == null) {
			order.setId(Sequence.getNextOrderId());
			order.setPayType(0);
			order.setDeleted(0);
			order.setPayStatus(false);
		}
		
		if (order.getPlanTime() != null) {
			if (order.getPlanStaff().getId() != null) {
				if (or.checkPlanTime(order)) {
					//预约时间不可用
					throw new LogicException(ErrorType.errorPlanTime);
				}
			} else {
				//TODO 随机分配员工
			}
		}
		or.addOrEditOrder(order);
		return order.getId().substring(0, 6);
	}

	public String checkPlan(Order order) {
		if (or.checkPlanTime(order)) {
			//			预约时间不可用
			throw new LogicException(ErrorType.errorPlanTime);
		}
		return ConstantsStatus.SUCCESS;
	}


	public Order getOrderById(String id) {
//		OrderInfoResult result =  or.getOrderById(id);
		return null;
	}
	
}
