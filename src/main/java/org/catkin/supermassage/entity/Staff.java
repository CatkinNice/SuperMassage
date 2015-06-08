package org.catkin.supermassage.entity;

import java.util.List;

import org.catkin.supermassage.entity.model.QueryParam;

/**
 * 
 * @author Catkin_nice
 *
 */
public class Staff extends QueryParam {
	private Long id;				//员工ID
	private Long storeId;			//店铺ID
	private String name;			//员工名字
	
	private String icon;			//员工头像
	private Boolean gender;			//性别（false:男，true:女）
	private Integer age;			//年龄
	
	private Integer experience;			//工作经验
	private String job;				//岗位
	private String remark;			//备注
	
	private Integer wokeStatus; 	//服务状态（-1：休假，0:空闲，1:预约，2：服务）
	private List<Long> packages;	//员工可服务的项目ID

	public Staff() {
	}

	public Staff(Long id, Integer wokeStatus) {
		this.id = id;
		this.wokeStatus = wokeStatus;
	}

	public Staff(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
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

	public Boolean getGender() {
		return gender;
	}

	public void setGender(Boolean gender) {
		this.gender = gender;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getExperience() {
		return experience;
	}

	public void setExperience(Integer experience) {
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

	public Integer getWokeStatus() {
		return wokeStatus;
	}

	public void setWokeStatus(Integer wokeStatus) {
		this.wokeStatus = wokeStatus;
	}

	public List<Long> getPackages() {
		return packages;
	}

	public void setPackages(List<Long> packages) {
		this.packages = packages;
	}
	
}
