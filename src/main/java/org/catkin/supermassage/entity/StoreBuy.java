package org.catkin.supermassage.entity;

import java.util.Date;

/**
 * 
 * @author Catkin_nice
 *
 */
public class StoreBuy {
	private Long id;			//购买ID
	private Long storeId;		//商铺ID
	private int staffNum;		//商铺员工数
	
	private float price;		//购买价格
	private Date buyTime;		//购买时间
	private Date startTime;		//服务开始时间
	
	private Date endTime;		//服务结束时间

	public StoreBuy() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public int getStaffNum() {
		return staffNum;
	}

	public void setStaffNum(int staffNum) {
		this.staffNum = staffNum;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Date getBuyTime() {
		return buyTime;
	}

	public void setBuyTime(Date buyTime) {
		this.buyTime = buyTime;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
}
