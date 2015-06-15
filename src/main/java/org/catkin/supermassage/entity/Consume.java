package org.catkin.supermassage.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 * @author Catkin_nice
 *
 */
@JsonInclude(Include.NON_NULL)
public class Consume {
	private String orderId;			//订单ID
	private Long storeId;			//店铺ID
	private Integer packageTime;	//服务时长ID
	private Staff planStaff;		//预约员工
	
	private Staff usedStaff;		//服务员工
	private Date planTime;			//预约时间
	private Date usedTime;			//使用时间
	private Long roomId;			//使用包间ID
	
	public Consume() {
	}

	/**
	 * 取空闲员工使用
	 * @param storeId 店铺ID
	 * @param packageTime 购买服务时长（分）
	 * @param time 预约时间或使用时间
	 */
	public Consume(Long storeId, Integer packageTime, Date time) {
		super();
		this.storeId = storeId;
		this.packageTime = packageTime;
		this.usedTime = time;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public Integer getPackageTime() {
		return packageTime;
	}

	public void setPackageTime(Integer packageTime) {
		this.packageTime = packageTime;
	}

	public Staff getPlanStaff() {
		return planStaff;
	}

	public void setPlanStaff(Staff planStaff) {
		this.planStaff = planStaff;
	}

	public Staff getUsedStaff() {
		return usedStaff;
	}

	public void setUsedStaff(Staff usedStaff) {
		this.usedStaff = usedStaff;
	}

	public Long getRoomId() {
		return roomId;
	}

	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}

	public Date getPlanTime() {
		return planTime;
	}

	public void setPlanTime(Date planTime) {
		this.planTime = planTime;
	}

	public Date getUsedTime() {
		return usedTime;
	}

	public void setUsedTime(Date usedTime) {
		this.usedTime = usedTime;
	}
	
}
