package org.catkin.supermassage.service.task;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.catkin.supermassage.entity.Consume;
import org.catkin.supermassage.entity.Order;
import org.catkin.supermassage.entity.Roome;
import org.catkin.supermassage.entity.Staff;
import org.catkin.supermassage.entity.model.MsgInfo;
import org.catkin.supermassage.repository.ConsumeRepository;
import org.catkin.supermassage.repository.OrderRepository;
import org.catkin.supermassage.repository.RoomeRepository;
import org.catkin.supermassage.repository.StaffRepository;
import org.catkin.supermassage.service.jms.JmsSender;
import org.catkin.supermassage.service.jms.MsgType;
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
	
	@Autowired
	private JmsSender jmsSender;
	
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
		List<Order> orders = new ArrayList<Order>();
		
		//员工预约中状态
		for (Consume consume : planConsumes) {
			Staff staff = consume.getPlanStaff();
			staff.setWokeStatus(ConstantsStatus.Staff.WOKE_STATUS_PLAN);
			staffs.add(staff);
		}
		
		//服务结束员工状态空闲，包间状态空闲
		for (Consume consume : usedConsumes) {
			Staff staff = consume.getPlanStaff();
			staff.setWokeStatus(ConstantsStatus.Staff.WOKE_STATUS_IDLE);
			Roome roome = new Roome(consume.getRoomId(), null, ConstantsStatus.Rooms.USE_STATUS_IDLE);
			staffs.add(staff);
			roomes.add(roome);
		}
		
		//包间休息结束状态
		for (Roome roome : breakupRoomes) {
			roome.setUseStatus(ConstantsStatus.Rooms.USE_STATUS_BREAKUP);
			roomes.add(roome);
		}
		
		//预约过期员工状态空闲,订单状态预约过期（发送过期消息给用户）
		for (Consume consume : expPlanConsumes) {
			Staff staff = consume.getPlanStaff();
			staff.setWokeStatus(ConstantsStatus.Staff.WOKE_STATUS_IDLE);
			staffs.add(staff);
			orders.add(new Order(consume.getOrderId(), null, null, ConstantsStatus.Order.USE_STATUS_EXPPANL));
		}
		
		if (!CollectionUtils.isEmpty(staffs)) {
			sr.editWokeStatus(staffs.toArray(new Staff[staffs.size()]));
		}
		if (!CollectionUtils.isEmpty(roomes)) {
			rr.editRoomeById(roomes.toArray(new Roome[roomes.size()]));
		}
		
		if (!CollectionUtils.isEmpty(orders)) {
			or.editOrder(orders.toArray(new Order[orders.size()]));
			
			//预约过期发送消息给用户
			MsgInfo<Object> msgInfo = new MsgInfo<Object>();
			Calendar calendar = Calendar.getInstance();
			msgInfo.setSendTime(calendar.getTime());
			msgInfo.setType(MsgType.user);
			
			for (Order order : orders) {
				Order ord = or.getOrderById(order.getId());
				msgInfo.setBody(ord);
				msgInfo.setReceiver(ord.getUserId());
				jmsSender.sendMessage(msgInfo);
			}
		}
	}
}
