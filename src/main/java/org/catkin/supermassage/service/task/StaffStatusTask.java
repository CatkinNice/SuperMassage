package org.catkin.supermassage.service.task;

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
public class StaffStatusTask {
	
	@Autowired
	private StaffRepository sr;
	
	@Autowired
	private RoomeRepository rr;
	
	@Autowired
	private OrderRepository or;
	
	@Autowired
	private ConsumeRepository cr;
	
	/**
	 * 以一分钟的固定间隔更新员工服务状态
	 */
	@Scheduled(initialDelay = 1000 * 60, fixedRate = 1000 * 60)
	public void run() {
		List<Consume> planConsumes = cr.getPlanConsumeByNow();
		List<Consume> usedConsumes = cr.getUsedConsumeByNow();
		List<Consume> expPlanConsumes = cr.getExpPlanConsumeByNow();
		
		//员工预约中状态
		if (!CollectionUtils.isEmpty(planConsumes)) {
			Staff[] staffs = new Staff[planConsumes.size()];
			for (int i = 0; i < staffs.length; i++) {
				Staff staff = planConsumes.get(i).getPlanStaff();
				staff.setWokeStatus(ConstantsStatus.Staff.WOKE_STATUS_PLAN);
				staffs[i] = staff;
			}
			sr.editWokeStatus(staffs);
		}
		
		//服务结束员工状态空闲，包间状态空闲
		if (!CollectionUtils.isEmpty(usedConsumes)) {
			Staff[] staffs = new Staff[usedConsumes.size()];
			Roome[] roomes = new Roome[usedConsumes.size()];
			
			for (int i = 0; i < staffs.length; i++) {
				Consume consume = planConsumes.get(i);
				Staff staff = consume.getPlanStaff();
				staff.setWokeStatus(ConstantsStatus.Staff.WOKE_STATUS_IDLE);
				staffs[i] = staff;
				roomes[i] = new Roome(consume.getRoomId(), null, ConstantsStatus.Rooms.USE_STATUS_IDLE);
			}
			
			sr.editWokeStatus(staffs);
			rr.editRoomeById(roomes);
		}
		
		//预约过期员工状态空闲（发送过期消息给用户）
		if (!CollectionUtils.isEmpty(expPlanConsumes)) {
			Staff[] staffs = new Staff[expPlanConsumes.size()];
			String[] orderIds = new String[expPlanConsumes.size()];
			
			for (int i = 0; i < staffs.length; i++) {
				Consume consume = planConsumes.get(i);
				Staff staff = consume.getPlanStaff();
				staff.setWokeStatus(ConstantsStatus.Staff.WOKE_STATUS_IDLE);
				staffs[i] = staff;
				orderIds[i] = consume.getOrderId();
			}
			sr.editWokeStatus(staffs);
			
			for (String id : orderIds) {
				//TODO 发送过期消息给用户
				Order order = or.getOrderById(id);
				order.getUserId();
			}
		}
	}
}
