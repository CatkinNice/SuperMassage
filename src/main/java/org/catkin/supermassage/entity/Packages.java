package org.catkin.supermassage.entity;

import java.util.List;

public class Packages extends QueryParam {
	private Long id;			//产品ID
	private Long storeId;		//商铺ID
	private String name;		//产品名称
	
	private float storePrice;	//商铺价格
	private float appPrice;		//APP价格
	private Integer timed;		//产品时长
	
	private String remark;		//备注
	private List<Long> staffs;  //可服务员工ID

	public Packages() {
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getStorePrice() {
		return storePrice;
	}

	public void setStorePrice(float storePrice) {
		this.storePrice = storePrice;
	}

	public float getAppPrice() {
		return appPrice;
	}

	public void setAppPrice(float appPrice) {
		this.appPrice = appPrice;
	}

	public Integer getTimed() {
		return timed;
	}

	public void setTimed(Integer timed) {
		this.timed = timed;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<Long> getStaffs() {
		return staffs;
	}

	public void setStaffs(List<Long> staffs) {
		this.staffs = staffs;
	}
	
}
