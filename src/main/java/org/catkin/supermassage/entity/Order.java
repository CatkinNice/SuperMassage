package org.catkin.supermassage.entity;

import java.util.Date;

/**
 * 
 * @author Catkin_nice
 *
 */
public class Order {
	private String id;				//订单ID
	private Long storeId;			//商铺ID
	private Long userId;			//用户ID
	private Packages packages;		//商品对象
	
	private Staff planStaff;		//预约员工对象
	private Staff usedStaff;		//服务员工对象	
	private Date planTime;			//预约时间
	private Date usedTime;			//使用时间
	
	private String payId;			//支付订单号（支付宝号）
	private Integer payType;		//支付类型（0：到店支付，1：在线支付）
	private Integer useStatus;		//使用状态（0：未支付，1：未使用，2：已使用）
	private Integer deleted;		//删除状态（0：正常，1：用户删除）
	
	private String storeName;		//店铺名称
	
	public Order() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public Integer getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(Integer useStatus) {
		this.useStatus = useStatus;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	public Packages getPackages() {
		return packages;
	}

	public void setPackages(Packages packages) {
		this.packages = packages;
	}

	public Staff getPlanStaff() {
		return planStaff == null ? new Staff() : planStaff;
	}

	public void setPlanStaff(Staff planStaff) {
		this.planStaff = planStaff;
	}

	public Staff getUsedStaff() {
		return usedStaff == null ? new Staff() : planStaff;
	}

	public void setUsedStaff(Staff usedStaff) {
		this.usedStaff = usedStaff;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	
}
