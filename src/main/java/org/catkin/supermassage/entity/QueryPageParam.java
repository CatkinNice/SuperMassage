package org.catkin.supermassage.entity;

public abstract class QueryPageParam {
	/**
	 * 起始位置
	 */
	private Integer from;
	
	/**
	 * 获取记录数
	 */
	private Integer size;

	public Integer getFrom() {
		return from;
	}

	public Integer getSize() {
		return size;
	}
	
	public void setPageParam(Integer from, Integer size) {
		this.from = from;
		this.size = size;
	}
	
	
}
