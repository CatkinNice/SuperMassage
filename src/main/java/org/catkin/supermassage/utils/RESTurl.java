package org.catkin.supermassage.utils;

/**
 * 
 * 服务地址
 * 
 * @author Catkin_nice
 *
 */
public interface RESTurl {
	/**
	 * 店铺
	 */
	String store = "/store";

	/**
	 * 店铺购买使用
	 */
	String storeBuy = "/store/buy";
	
	/**
	 * 员工
	 */
	String staff = "/staff";
	
	/**
	 * 服务项目（商品）
	 */
	String packages = "/packages";
	
	/**
	 * 服务包间
	 */
	String roome = "/roome";
	
	/**
	 * 用户
	 */
	String user = "/user";
	
	/**
	 * 订单
	 */
	String order = "/order";
	
	/**
	 * 消费记录
	 */
	String consume = "/consume";
}
