package org.catkin.supermassage.entity;

import java.util.List;

/**
 * 
 * @author Catkin_nice
 *
 */
public class PageResult {
	
	private List<?> data;
	private int totalSize;
	
	public PageResult(List<?> data, int totalSize) {
		this.data = data;
		this.totalSize = totalSize;
	}

	public List<?> getData() {
		return data;
	}

	public void setData(List<?> data) {
		this.data = data;
	}

	public int getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}
	
}
