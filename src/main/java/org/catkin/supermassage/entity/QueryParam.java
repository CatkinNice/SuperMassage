package org.catkin.supermassage.entity;

public class QueryParam {
	/**
	 * search Key
	 */
	private String key;
	
	/**
	 * 起始位置
	 */
	private Integer from;
	
	/**
	 * 获取记录数
	 */
	private Integer size;
	
	public QueryParam() {
	}

	public QueryParam(String key, Integer from, Integer size) {
		this.key = key;
		this.from = from;
		this.size = size;
	}

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

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
}
