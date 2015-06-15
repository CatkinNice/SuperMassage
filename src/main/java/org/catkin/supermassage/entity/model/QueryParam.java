package org.catkin.supermassage.entity.model;

public class QueryParam {
	/**
	 * search Key
	 */
	private String key;
	
	/**
	 * 页码
	 */
	private Integer page;
	
	/**
	 * 页记录数
	 */
	private Integer size;
	
	public QueryParam() {
	}

	public QueryParam(String key, Integer page, Integer size) {
		this.key = key;
		this.page = page;
		this.size = size;
	}

	public Integer getPage() {
		return page;
	}

	public Integer getSize() {
		return size;
	}
	
	public void setPage(Integer page) {
		this.page = page;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
}
