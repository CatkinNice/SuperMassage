package org.catkin.supermassage.entity;

import java.util.Date;

/**
 * 
 * @author Catkin_nice
 *
 */
public class Roome {
	private Long id;			//包间ID
	private Long storeId;		//店铺ID
	private String name;		//包间名称
	private int bedNum;			//服务位
	
	private String remark;		//描述
	private Date endTime;		//服务结束时间
	private int useStatus;	//服务状态（-1：装修，0：空闲，1：使用，2：休息，3：休息结束）
	
	public Roome() {
		super();
	}

	public Roome(Long id, Date endTime, int useStatus) {
		this.id = id;
		this.endTime = endTime;
		this.useStatus = useStatus;
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

	public int getBedNum() {
		return bedNum;
	}

	public void setBedNum(int bedNum) {
		this.bedNum = bedNum;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public int getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(int useStatus) {
		this.useStatus = useStatus;
	}
	
}
