package org.catkin.supermassage.service;

import java.util.Date;

import org.catkin.supermassage.entity.Consume;
import org.catkin.supermassage.entity.Order;
import org.catkin.supermassage.repository.ConsumeRepository;
import org.catkin.supermassage.repository.OrderRepository;
import org.catkin.supermassage.utils.ConstantsStatus;
import org.catkin.supermassage.utils.ErrorType;
import org.catkin.supermassage.utils.LogicException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author Catkin_nice
 *
 */
@Service
public class ConsumeService {
	
	@Autowired
	private OrderRepository or;
	
	@Autowired
	private ConsumeRepository cr;

	public void editConsume(Consume consume) {
		Order order = or.getOrderById(consume.getOrderId());
		
		//判断订单是否支付
		if (ConstantsStatus.ORDER_PAYTYPE_STORE != order.getPayType()
				&& ConstantsStatus.ORDER_USESTATUS_NOTPAY == order.getUseStatus()) {
			throw new LogicException(ErrorType.errorOrderNoPay);
		}
		
		if (consume.getPackageTime() == null) {
			consume.setPackageTime(order.getPackages().getTimed());
		}
		
		if (consume.getUsedStaff() != null && consume.getRoomId() != null) {
			//修改订单状态（已使用）
			//修改包间状态（使用中）
			//修改技师状态（服务中）
			consume.setUsedTime(new Date());
		} else {
			if (consume.getPlanStaff() == null) {
				//TODO 随机分配员工
			} else {
				checkPlan(consume);
			}
		}
		
		cr.addOrEditConcume(consume);
	}
	
	public String checkPlan(Consume consume) {
		if (cr.checkPlanTime(consume)) {
			//预约时间不可用
			throw new LogicException(ErrorType.errorPlanTime);
		}
		return ConstantsStatus.SUCCESS;
	}
	
}
