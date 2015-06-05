package org.catkin.supermassage.entity;

import java.util.Date;

/**
 * 
 * @author Catkin_nice
 *
 */
public class Order {
	private Long id;				//订单ID
	private Long storeId;			//商铺ID
	private Long userId;			//用户ID
	private Long packageId;			//商品ID
	
	private float appPrice;			//APP订单总价
	private float storePrice;		//商铺订单总价
	private Long planStaffId;		//预约员工ID
	private Long usedStaffId;		//服务员工ID
	
	private Date planTime;			//预约时间
	private Date usedTime;			//使用时间
	private String payId;			//支付订单号（支付宝号）
	private int payType;			//支付类型（0：到店支付，1：在线支付）
	
	private boolean payStatus;		//支付状态（0：未支付，1：已支付）
	private int deleted;			//删除状态（0：正常，1：用户删除）
	
	public Order() {
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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getPackageId() {
		return packageId;
	}

	public void setPackageId(Long packageId) {
		this.packageId = packageId;
	}

	public float getAppPrice() {
		return appPrice;
	}

	public void setAppPrice(float appPrice) {
		this.appPrice = appPrice;
	}

	public float getStorePrice() {
		return storePrice;
	}

	public void setStorePrice(float storePrice) {
		this.storePrice = storePrice;
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

	public String getPayId() {
		return payId;
	}

	public void setPayId(String payId) {
		this.payId = payId;
	}

	public int getPayType() {
		return payType;
	}

	public void setPayType(int payType) {
		this.payType = payType;
	}

	public boolean isPayStatus() {
		return payStatus;
	}

	public void setPayStatus(boolean payStatus) {
		this.payStatus = payStatus;
	}

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	
}
