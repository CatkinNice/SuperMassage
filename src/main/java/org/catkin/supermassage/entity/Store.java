package org.catkin.supermassage.entity;

public class Store {

	private Long id;				//商铺ID
	private String name;			//商铺名称
	private String pwd;				//商铺管理密码
	
	private String longLatItude;	//经纬度
	private String address;			//商铺地址
	private String phone;			//商铺电话
	
	private String remark;			//备注

	public Store() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getLongLatItude() {
		return longLatItude;
	}

	public void setLongLatItude(String longLatItude) {
		this.longLatItude = longLatItude;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRemark() {
		return remark ;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
