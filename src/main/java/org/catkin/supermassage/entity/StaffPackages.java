package org.catkin.supermassage.entity;

/**
 * 
 * @author Catkin_nice
 *
 */
public class StaffPackages {
	private long staffId;		//员工ID
	private long packagesId;	//服务项目ID
	
	public StaffPackages(long staffId, long packagesId) {
		this.staffId = staffId;
		this.packagesId = packagesId;
	}

	public long getStaffId() {
		return staffId;
	}

	public void setStaffId(long staffId) {
		this.staffId = staffId;
	}

	public long getPackagesId() {
		return packagesId;
	}

	public void setPackagesId(long packagesId) {
		this.packagesId = packagesId;
	}
	
}
