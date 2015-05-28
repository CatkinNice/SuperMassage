package org.catkin.supermassage.entity;

import java.util.Date;

/**
 * 
 * @author Catkin_nice
 *
 */
public class OrderItem {
	private Long id;			//订单项ID
	private Long packageId;		//产品ID
	private Long planStaffId;	//预约技师ID
	
	private Long usedStaffId;	//服务技师ID
	private Date planTime;		//预约时间
	private Date usedTime;		//使用时间
	
	public OrderItem() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPackageId() {
		return packageId;
	}

	public void setPackageId(Long packageId) {
		this.packageId = packageId;
	}

	public Long getPlanStaffId() {
		return planStaffId;
	}

	public void setPlanStaffId(Long planStaffId) {
		this.planStaffId = planStaffId;
	}

	public Long getUsedStaffId() {
		return usedStaffId;
	}

	public void setUsedStaffId(Long usedStaffId) {
		this.usedStaffId = usedStaffId;
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
