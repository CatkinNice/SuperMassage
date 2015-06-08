package org.catkin.supermassage.service;

import org.catkin.supermassage.entity.Consume;
import org.catkin.supermassage.entity.Order;
import org.catkin.supermassage.repository.ConsumeRepository;
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
	private ConsumeRepository cr;

	public void editConsume(Consume consume) {
		//查询订单是否支付或是到店支付
		
		if (consume.getUsedTime() == null && consume.getPlanTime() != null) {//预约
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
