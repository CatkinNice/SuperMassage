package org.catkin.supermassage.service.task;

import java.util.ArrayList;
import java.util.List;

import org.catkin.supermassage.entity.Consume;
import org.catkin.supermassage.entity.Order;
import org.catkin.supermassage.entity.Roome;
import org.catkin.supermassage.entity.Staff;
import org.catkin.supermassage.repository.ConsumeRepository;
import org.catkin.supermassage.repository.OrderRepository;
import org.catkin.supermassage.repository.RoomeRepository;
import org.catkin.supermassage.repository.StaffRepository;
import org.catkin.supermassage.utils.ConstantsStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
public class StaffAndRoomeStatusTask {
	
	@Autowired
	private StaffRepository sr;
	
	@Autowired
	private RoomeRepository rr;
	
	@Autowired
	private OrderRepository or;
	
	@Autowired
	private ConsumeRepository cr;
	
	/**
	 * 以一分钟的固定间隔更新员工、包间服务状态
	 */
	@Scheduled(initialDelay = 1000 * 60, fixedRate = 1000 * 60)
	public void run() {
		List<Consume> planConsumes = cr.getPlanConsumeByNow();
		List<Consume> usedConsumes = cr.getUsedConsumeByNow();
		List<Consume> expPlanConsumes = cr.getExpPlanConsumeByNow();
		List<Roome> breakupRoomes = rr.getBreakupRoomeByNow();
		
		List<Staff> staffs = new ArrayList<Staff>();
		List<Roome> roomes = new ArrayList<Roome>();
		List<String> orderIds = new ArrayList<String>();
		
		//员工预约中状态
		if (!CollectionUtils.isEmpty(planConsumes)) {
			for (Consume consume : planConsumes) {
				Staff staff = consume.getPlanStaff();
				staff.setWokeStatus(ConstantsStatus.Staff.WOKE_STATUS_PLAN);
				staffs.add(staff);
			}
		}
		
		//服务结束员工状态空闲，包间状态空闲
		if (!CollectionUtils.isEmpty(usedConsumes)) {
			for (Consume consume : usedConsumes) {
				Staff staff = consume.getPlanStaff();
				staff.setWokeStatus(ConstantsStatus.Staff.WOKE_STATUS_IDLE);
				Roome roome = new Roome(consume.getRoomId(), null, ConstantsStatus.Rooms.USE_STATUS_IDLE);
				staffs.add(staff);
				roomes.add(roome);
			}
		}
		
		//包间休息结束状态
		if (!CollectionUtils.isEmpty(breakupRoomes)) {
			for (Roome roome : breakupRoomes) {
				roome.setUseStatus(ConstantsStatus.Rooms.USE_STATUS_BREAKUP);
				roomes.add(roome);
			}
		}
		
		//预约过期员工状态空闲（发送过期消息给用户）
		if (!CollectionUtils.isEmpty(expPlanConsumes)) {
			for (Consume consume : expPlanConsumes) {
				Staff staff = consume.getPlanStaff();
				staff.setWokeStatus(ConstantsStatus.Staff.WOKE_STATUS_IDLE);
				staffs.add(staff);
				orderIds.add(consume.getOrderId());
			}
		}
		
		sr.editWokeStatus(staffs.toArray(new Staff[staffs.size()]));
		rr.editRoomeById(roomes.toArray(new Roome[roomes.size()]));
		//TODO 预约过期发送消息给用户
		for (String id : orderIds) {
			Order order = or.getOrderById(id);
			order.getUserId();
		}
	}
}
