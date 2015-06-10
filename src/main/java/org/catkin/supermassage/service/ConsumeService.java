package org.catkin.supermassage.service;

import java.util.Date;
import java.util.Calendar;

import org.catkin.supermassage.entity.Order;
import org.catkin.supermassage.entity.Roome;
import org.catkin.supermassage.entity.Staff;
import org.catkin.supermassage.entity.Consume;
import org.catkin.supermassage.repository.OrderRepository;
import org.catkin.supermassage.repository.RoomeRepository;
import org.catkin.supermassage.repository.StaffRepository;
import org.catkin.supermassage.repository.ConsumeRepository;
import org.catkin.supermassage.utils.ErrorType;
import org.catkin.supermassage.utils.LogicException;
import org.catkin.supermassage.utils.ConstantsStatus;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

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
	
	@Autowired
	private RoomeRepository rr;
	
	@Autowired
	private StaffRepository sr;
	
	@Autowired
	private StaffService ss;

	public void addOrEditConsume(Consume consume) {
		Order order = or.getOrderById(consume.getOrderId());
		
		//判断订单是否支付
		if (ConstantsStatus.Order.PAY_TYPE_STORE != order.getPayType()
				&& ConstantsStatus.Order.USE_STATUS_NOTPAY == order.getUseStatus()) {
			throw new LogicException(ErrorType.errorOrderNotPay);
		}
		
		if (consume.getPackageTime() == null) {
			consume.setPackageTime(order.getPackages().getTimed());
		}
		
		if (consume.getStoreId() == null) {
			consume.setStoreId(order.getStoreId());
		}
		
		if (consume.getUsedStaff() != null && consume.getRoomId() != null) {
			Calendar calendar = Calendar.getInstance();
			consume.setUsedTime(calendar.getTime());
			//修改订单状态（已使用）
			order.setUseStatus(ConstantsStatus.Order.USE_STATUS_USED);
			
			//修改技师状态（服务中）
			sr.editWokeStatus(new Staff(consume.getUsedStaff().getId(), ConstantsStatus.Staff.WOKE_STATUS_RUN));
			
			//修改包间状态（使用中）
			rr.editRoomeById(new Roome(consume.getRoomId(), null, ConstantsStatus.Rooms.USE_STATUS_USE));
		} else {
			if (consume.getPlanStaff() == null) {
				//随机获取员工
				Long storeId = consume.getStoreId();
				Integer packageTimed = consume.getPackageTime();
				Date useTime = consume.getPlanTime();
				Staff staff = ss.getRandomStaff(storeId, packageTimed, useTime);
				consume.setPlanStaff(staff);
			} else {
				checkPlan(consume);
			}
			//修改订单状态（已预约）
			order.setUseStatus(ConstantsStatus.Order.USE_STATUS_PANL);
		}
		
		or.editOrder(order);
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
