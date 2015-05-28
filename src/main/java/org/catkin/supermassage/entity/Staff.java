package org.catkin.supermassage.entity;

/**
 * 
 * @author Catkin_nice
 *
 */
public class Staff {
	private Long id;			//员工ID
	private Long storeId;		//店铺ID
	private String name;		//员工名字
	
	private String icon;		//员工头像
	private boolean sex;		//性别（false:男，true:女）
	private Integer age;		//年龄
	
	private int experience;		//工作经验
	private String job;			//岗位
	private String remark;		//备注
	
	private boolean wokeStatus; //服务状态（false:空闲，true:服务中）

	public Staff() {
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

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public boolean isSex() {
		return sex;
	}

	public void setSex(boolean sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public boolean isWokeStatus() {
		return wokeStatus;
	}

	public void setWokeStatus(boolean wokeStatus) {
		this.wokeStatus = wokeStatus;
	}
	
}
