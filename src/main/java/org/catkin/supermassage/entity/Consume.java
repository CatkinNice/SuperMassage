package org.catkin.supermassage.entity;

import java.util.Date;

/**
 * 
 * @author Catkin_nice
 *
 */
public class Consume {
	private String id;				//消费ID(订单ID)
	private Integer packageTime;	//服务时长ID
	private Staff planStaff;		//预约员工
	private Staff usedStaff;		//服务员工
	
	private Date planTime;			//预约时间
	private Date usedTime;			//使用时间
	private Long roomId;			//使用包间ID
	
	public Consume() {
	}

	public Consume(String id, Integer packageTime) {
		this.id = id;
		this.packageTime = packageTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
